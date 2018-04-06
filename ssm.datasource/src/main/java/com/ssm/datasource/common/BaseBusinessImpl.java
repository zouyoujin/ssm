package com.ssm.datasource.common;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.enums.SqlMethod;
import com.baomidou.mybatisplus.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.SqlHelper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.toolkit.ReflectionKit;
import com.ssm.common.constant.Constants;
import com.ssm.common.exception.ServiceException;
import com.ssm.common.model.BaseModel;
import com.ssm.common.utils.DataUtil;
import com.ssm.common.utils.InstanceUtil;
import com.ssm.common.utils.PropertiesUtil;
import com.ssm.datasource.model.BaseMapper;

//import top.ibase4j.core.support.dbcp.HandleDataSource;
//import top.ibase4j.core.util.CacheUtil;
//import top.ibase4j.core.util.ExceptionUtil;

public abstract class BaseBusinessImpl<T extends BaseModel> implements BaseBusiness<T> {

	private static final Logger logger = LoggerFactory.getLogger(BaseBusinessImpl.class);

	@Resource
	public SqlSessionTemplate sqlSessionTemplate;

	@Autowired
	protected BaseMapper<T> mapper;

	int maxThread = PropertiesUtil.getInt("db.reader.list.maxThread", 50);
	int threadSleep = PropertiesUtil.getInt("db.reader.list.threadWait", 5);
	ExecutorService executorService = Executors.newFixedThreadPool(maxThread);

	/**
	 * 分页查询
	 * 
	 * @param params
	 * @return
	 */
	@SuppressWarnings({ "unchecked" })
	public static Page<Long> getPage(Map<String, Object> params) {
		Integer current = 1;
		Integer size = 10;
		String orderBy = "id_", sortAsc = null, openSort = "Y";
		if (DataUtil.isNotEmpty(params.get("pageNumber"))) {
			current = Integer.valueOf(params.get("pageNumber").toString());
		}
		if (DataUtil.isNotEmpty(params.get("pageIndex"))) {
			current = Integer.valueOf(params.get("pageIndex").toString());
		}
		if (DataUtil.isNotEmpty(params.get("pageSize"))) {
			size = Integer.valueOf(params.get("pageSize").toString());
		}
		if (DataUtil.isNotEmpty(params.get("limit"))) {
			size = Integer.valueOf(params.get("limit").toString());
		}
		if (DataUtil.isNotEmpty(params.get("offset"))) {
			current = Integer.valueOf(params.get("offset").toString()) / size + 1;
		}
		if (DataUtil.isNotEmpty(params.get("sort"))) {
			orderBy = (String) params.get("sort");
			params.remove("sort");
		}
		if (DataUtil.isNotEmpty(params.get("orderBy"))) {
			orderBy = (String) params.get("orderBy");
			params.remove("orderBy");
		}
		if (DataUtil.isNotEmpty(params.get("sortAsc"))) {
			sortAsc = (String) params.get("sortAsc");
			params.remove("sortAsc");
		}
		if (DataUtil.isNotEmpty(params.get("openSort"))) {
			openSort = (String) params.get("openSort");
			params.remove("openSort");
		}
		Object filter = params.get("filter");
		if (filter != null) {
			params.putAll(JSON.parseObject(filter.toString(), Map.class));
		}
		if (size == -1) {
			Page<Long> page = new Page<Long>();
			page.setOrderByField(orderBy);
			page.setAsc("Y".equals(sortAsc));
			page.setOpenSort("Y".equals(openSort));
			return page;
		}
		Page<Long> page = new Page<Long>(current, size, orderBy);
		page.setAsc("Y".equals(sortAsc));
		page.setOpenSort("Y".equals(openSort));
		return page;
	}

	/**
	 * @param id
	 * @param userId
	 */
	@Transactional
	public void del(List<Long> ids, Long userId) {
		for (Long id : ids) {
			del(id, userId);
		}
	}

	/**
	 * @param id
	 * @param userId
	 */
	@Transactional
	public void del(Long id, Long userId) {
		try {
			T record = this.queryById(id);
			record.setEnable(0);
			record.setUpdateTime(new Date());
			record.setUpdateBy(userId);
			mapper.updateById(record);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	/**
	 * @param id
	 */
	@Transactional
	public void delete(Long id) {
		try {
			mapper.deleteById(id);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	/**
	 * @param t
	 * @return
	 */
	@Transactional
	public Integer deleteByEntity(T t) {
		Wrapper<T> wrapper = new EntityWrapper<T>(t);
		return mapper.delete(wrapper);
	}

	/**
	 * @param columnMap
	 * @return
	 */
	@Transactional
	public Integer deleteByMap(Map<String, Object> columnMap) {
		return mapper.deleteByMap(columnMap);
	}

	/**
	 * 根据Id查询(默认类型T)
	 * 
	 * @param ids
	 * @return
	 */
	public List<T> getListByIds(final List<Long> ids) {
		final List<T> list = InstanceUtil.newArrayList();
		if (ids != null) {
			for (int i = 0; i < ids.size(); i++) {
				list.add(null);
			}
			final Map<Integer, Object> thread = InstanceUtil.newConcurrentHashMap();
			//final String datasource = HandleDataSource.getDataSource();
			for (int i = 0; i < ids.size(); i++) {
				final int index = i;
				executorService.execute(new Runnable() {
					public void run() {
						//HandleDataSource.putDataSource(datasource);
						try {
							list.set(index, queryById(ids.get(index)));
						} finally {
							thread.put(index, 0);
						}
					}
				});
			}
			while (thread.size() < list.size()) {
				try {
					Thread.sleep(threadSleep);
				} catch (InterruptedException e) {
					logger.error("", e);
				}
			}
		}
		return list;
	}

	/**
	 * 根据Id查询(cls返回类型Class)
	 * 
	 * @param ids
	 * @param cls
	 * @return
	 */
	public <K> List<K> getListByIds(final List<Long> ids, final Class<K> cls) {
		final List<K> list = InstanceUtil.newArrayList();
		if (ids != null) {
			for (int i = 0; i < ids.size(); i++) {
				list.add(null);
			}
			final Map<Integer, Object> thread = InstanceUtil.newConcurrentHashMap();
			//final String datasource = HandleDataSource.getDataSource();
			for (int i = 0; i < ids.size(); i++) {
				final int index = i;
				executorService.execute(new Runnable() {
					public void run() {
						//HandleDataSource.putDataSource(datasource);
						try {
							T t = queryById(ids.get(index));
							K k = InstanceUtil.to(t, cls);
							list.set(index, k);
						} finally {
							thread.put(index, 0);
						}
					}
				});
			}
			while (thread.size() < list.size()) {
				try {
					Thread.sleep(threadSleep);
				} catch (InterruptedException e) {
					logger.error("", e);
				}
			}
		}
		return list;
	}

	/**
	 * 根据Id查询(默认类型T)
	 * 
	 * @param ids
	 * @return
	 */
	public Page<Map<String, Object>> getPageMap(final Page<Long> ids) {
		if (ids != null) {
			Page<Map<String, Object>> page = new Page<Map<String, Object>>(ids.getCurrent(), ids.getSize());
			page.setTotal(ids.getTotal());
			final List<Map<String, Object>> records = InstanceUtil.newArrayList();
			for (int i = 0; i < ids.getRecords().size(); i++) {
				records.add(null);
			}
			final Map<Integer, Object> thread = InstanceUtil.newConcurrentHashMap();
			//final String datasource = HandleDataSource.getDataSource();
			for (int i = 0; i < ids.getRecords().size(); i++) {
				final int index = i;
				executorService.execute(new Runnable() {
					public void run() {
						//HandleDataSource.putDataSource(datasource);
						try {
							records.set(index, InstanceUtil.transBean2Map(queryById(ids.getRecords().get(index))));
						} finally {
							thread.put(index, 0);
						}
					}
				});
			}
			while (thread.size() < records.size()) {
				try {
					Thread.sleep(threadSleep);
				} catch (InterruptedException e) {
					logger.error("", e);
				}
			}
			page.setRecords(records);
			return page;
		}
		return new Page<Map<String, Object>>();
	}

	/**
	 * @param params
	 * @return
	 */
	public Page<T> query(Map<String, Object> params) {
		Page<Long> page = getPage(params);
		page.setRecords(mapper.selectIdPage(page, params));
		return getPage(page);
	}

	public Page<T> query(T params, Pagination pagination) {
		Page<Long> page = new Page<Long>();
		try {
			PropertyUtils.copyProperties(page, pagination);
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			logger.error("", e);
		}
		page.setRecords(mapper.selectIdPage(page, params));
		return getPage(page);
	}

	/**
	 * @param id
	 * @return
	 */
	public T queryById(Long id) {
		return queryById(id, 1);
	}

	/**
	 * @param params
	 * @return
	 */
	public List<T> queryList(Map<String, Object> params) {
		List<Long> ids = mapper.selectIdPage(params);
		List<T> list = getListByIds(ids);
		return list;
	}

	public List<T> getList(T params) {
		List<Long> ids = mapper.selectIdPage(params);
		List<T> list = getListByIds(ids);
		return list;
	}

	/**
	 * @param entity
	 * @return
	 */
	public List<T> selectList(Wrapper<T> entity) {
		return mapper.selectList(entity);
	}

	/**
	 * @param entity
	 * @return
	 */
	public T selectOne(T entity) {
		return mapper.selectOne(entity);
	}

	/**
	 * @param record
	 * @return
	 */
	@Transactional
	public T update(T record) {
		try {
			record.setUpdateTime(new Date());
			if (record.getId() == null) {
				record.setCreateTime(new Date());
				mapper.insert(record);
			} else {
				//String lockKey = getLockKey("U" + record.getId());
				//if (CacheUtil.getLock(lockKey, "更新", DateUtil.addDate(new Date(), Calendar.SECOND, 10))) {
					try {
						mapper.updateById(record);
					} finally {
						//CacheUtil.unLock(lockKey);
					}
				//} else {
				//	throw new RuntimeException("数据不一致!请刷新页面重新编辑!");
				//}
			}
			record = mapper.selectById(record.getId());
			try {
				//CacheUtil.getCache().set(getCacheKey(record.getId()), record);
			} catch (Exception e) {
				logger.error(Constants.Exception_Head, e);
			}
		} catch (DuplicateKeyException e) {
			logger.error(Constants.Exception_Head, e);
			throw new ServiceException("已经存在相同的记录.");
		} catch (Exception e) {
			logger.error(Constants.Exception_Head, e);
			throw new RuntimeException(e);
			//throw new RuntimeException(ExceptionUtil.getStackTraceAsString(e));
		}
		return record;
	}

	@Transactional
	public T updateAllColumn(T record) {
		try {
			record.setUpdateTime(new Date());
			if (record.getId() == null) {
				record.setCreateTime(new Date());
				mapper.insert(record);
			} else {
				//String lockKey = getLockKey("U" + record.getId());
				//if (CacheUtil.getLock(lockKey, "更新所有字段", DateUtil.addDate(new Date(), Calendar.SECOND, 10))) {
					try {
						mapper.updateAllColumnById(record);
					} finally {
						//CacheUtil.unLock(lockKey);
					}
				//} else {
				//	throw new RuntimeException("数据不一致!请刷新页面重新编辑!");
				//}
			}
			try {
				record = mapper.selectById(record.getId());
				//CacheUtil.getCache().set(getCacheKey(record.getId()), record);
			} catch (Exception e) {
				logger.error(Constants.Exception_Head, e);
			}
		} catch (DuplicateKeyException e) {
			logger.error(Constants.Exception_Head, e);
			throw new ServiceException("已经存在相同的记录.");
		} catch (Exception e) {
			logger.error(Constants.Exception_Head, e);
			throw new RuntimeException(e);
			//throw new RuntimeException(ExceptionUtil.getStackTraceAsString(e));
		}
		return record;
	}

	@Transactional(rollbackFor = Exception.class)
	public boolean updateAllColumnBatch(List<T> entityList) {
		return updateAllColumnBatch(entityList, 30);
	}

	@Transactional(rollbackFor = Exception.class)
	public boolean updateAllColumnBatch(List<T> entityList, int batchSize) {
		return updateBatch(entityList, batchSize, false);
	}

	@Transactional(rollbackFor = Exception.class)
	public boolean updateBatch(List<T> entityList) {
		return updateBatch(entityList, 30);
	}

	@Transactional(rollbackFor = Exception.class)
	public boolean updateBatch(List<T> entityList, int batchSize) {
		return updateBatch(entityList, batchSize, true);
	}

	@SuppressWarnings("unchecked")
	protected Class<T> currentModelClass() {
		return ReflectionKit.getSuperClassGenricType(getClass(), 0);
	}

	/**
	 * 获取缓存键值
	 * 
	 * @param id
	 * @return
	 */
	protected String getCacheKey(Object id) {
		String cacheName = getCacheKey();
		return new StringBuilder(Constants.CACHE_NAMESPACE).append(cacheName).append(":").append(id).toString();
	}

	/**
	 * 获取缓存键值
	 * 
	 * @param id
	 * @return
	 */
	protected String getLockKey(Object id) {
		String cacheName = getCacheKey();
		return new StringBuilder(Constants.CACHE_NAMESPACE).append(cacheName).append(":LOCK:").append(id).toString();
	}

	/**
	 * @param params
	 * @param cls
	 * @return
	 */
	protected <P> Page<P> query(Map<String, Object> params, Class<P> cls) {
		Page<Long> page = getPage(params);
		page.setRecords(mapper.selectIdPage(page, params));
		return getPage(page, cls);
	}

	/**
	 * @param millis
	 */
	protected void sleep(int millis) {
		try {
			Thread.sleep(RandomUtils.nextLong(10, millis));
		} catch (InterruptedException e) {
			logger.error("", e);
		}
	}

	/**
	 * <p>
	 * 批量操作 SqlSession
	 * </p>
	 */
	protected SqlSession sqlSessionBatch() {
		return SqlHelper.sqlSessionBatch(currentModelClass());
	}

	/**
	 * 获取SqlStatement
	 *
	 * @param sqlMethod
	 * @return
	 */
	protected String sqlStatement(SqlMethod sqlMethod) {
		return SqlHelper.table(currentModelClass()).getSqlStatement(sqlMethod.getMethod());
	}

	/**
	 * @return
	 */
	private String getCacheKey() {
		Class<?> cls = getClass();
		String cacheName = Constants.cacheKeyMap.get(cls);
		if (DataUtil.isEmpty(cacheName)) {
			CacheConfig cacheConfig = cls.getAnnotation(CacheConfig.class);
			if (cacheConfig != null && ArrayUtils.isNotEmpty(cacheConfig.cacheNames())) {
				cacheName = cacheConfig.cacheNames()[0];
			} else {
				cacheName = getClass().getName();
			}
			Constants.cacheKeyMap.put(cls, cacheName);
		}
		return cacheName;
	}

	/** 根据Id查询(默认类型T) */
	protected Page<T> getPage(final Page<Long> ids) {
		if (ids != null) {
			Page<T> page = new Page<T>(ids.getCurrent(), ids.getSize());
			page.setTotal(ids.getTotal());
			final List<T> records = InstanceUtil.newArrayList();
			for (int i = 0; i < ids.getRecords().size(); i++) {
				records.add(null);
			}
			final Map<Integer, Object> thread = InstanceUtil.newConcurrentHashMap();
			//final String datasource = HandleDataSource.getDataSource();
			for (int i = 0; i < ids.getRecords().size(); i++) {
				final int index = i;
				executorService.execute(new Runnable() {
					public void run() {
						//HandleDataSource.putDataSource(datasource);
						try {
							records.set(index, queryById(ids.getRecords().get(index)));
						} finally {
							thread.put(index, 0);
						}
					}
				});
			}
			while (thread.size() < records.size()) {
				try {
					Thread.sleep(threadSleep);
				} catch (InterruptedException e) {
					logger.error("", e);
				}
			}
			page.setRecords(records);
			return page;
		}
		return new Page<T>();
	}

	/** 根据Id查询(cls返回类型Class) */
	private <K> Page<K> getPage(final Page<Long> ids, final Class<K> cls) {
		if (ids != null) {
			Page<K> page = new Page<K>(ids.getCurrent(), ids.getSize());
			page.setTotal(ids.getTotal());
			final List<K> records = InstanceUtil.newArrayList();
			for (int i = 0; i < ids.getRecords().size(); i++) {
				records.add(null);
			}
			final Map<Integer, Object> thread = InstanceUtil.newConcurrentHashMap();
			//final String datasource = HandleDataSource.getDataSource();
			for (int i = 0; i < ids.getRecords().size(); i++) {
				final int index = i;
				executorService.execute(new Runnable() {
					public void run() {
						//HandleDataSource.putDataSource(datasource);
						try {
							T t = queryById(ids.getRecords().get(index));
							K k = InstanceUtil.to(t, cls);
							records.set(index, k);
						} finally {
							thread.put(index, 0);
						}
					}
				});
			}
			while (thread.size() < records.size()) {
				try {
					Thread.sleep(threadSleep);
				} catch (InterruptedException e) {
					logger.error("", e);
				}
			}
			page.setRecords(records);
			return page;
		}
		return new Page<K>();
	}

	private T queryById(Long id, int times) {
//		String key = getCacheKey(id);
//		T record = null;
//		try {
//			record = (T) CacheUtil.getCache().getFire(key);
//		} catch (Exception e) {
//			logger.error(Constants.Exception_Head, e);
//		}
//		if (record == null) {
//			String lockKey = getLockKey(id);
//			if (CacheUtil.getLock(lockKey, "根据ID查询数据", DateUtil.addDate(new Date(), Calendar.SECOND, 10))) {
//				try {
//					record = mapper.selectById(id);
//					try {
//						CacheUtil.getCache().set(key, record);
//					} catch (Exception e) {
//						logger.error(Constants.Exception_Head, e);
//					}
//				} finally {
//					CacheUtil.unLock(lockKey);
//				}
//			} else {
//				if (times > 3) {
//					record = mapper.selectById(id);
//					try {
//						CacheUtil.getCache().set(key, record);
//					} catch (Exception e) {
//						logger.error(Constants.Exception_Head, e);
//					}
//				} else {
//					logger.debug(getClass().getSimpleName() + ":" + id + " retry queryById.");
//					sleep(20);
//					return queryById(id, times + 1);
//				}
//			}
//		}
		T record = mapper.selectById(id);
		return record;
	}

	private boolean updateBatch(List<T> entityList, int batchSize, boolean selective) {
		if (CollectionUtils.isEmpty(entityList)) {
			throw new IllegalArgumentException("Error: entityList must not be empty");
		}
		try (SqlSession batchSqlSession = sqlSessionBatch()) {
			int size = entityList.size();
			for (int i = 0; i < size; i++) {
				if (selective) {
					update(entityList.get(i));
				} else {
					updateAllColumn(entityList.get(i));
				}
				if (i >= 1 && i % batchSize == 0) {
					batchSqlSession.flushStatements();
				}
			}
			batchSqlSession.flushStatements();
		} catch (Throwable e) {
			throw new MybatisPlusException("Error: Cannot execute insertOrUpdateBatch Method. Cause", e);
		}
		return true;
	}

	// @Override
	// public int insertByMethod(String statement, T record) throws
	// BusinessException {
	// return sqlSessionTemplate.insert(statement, record);
	// }
	//
	// @Override
	// public int insert(T record) {
	// return baseMapper.insert(record);
	// }
	//
	// @Override
	// public int insertSelective(T record) {
	// return baseMapper.insertSelective(record);
	// }
	//
	// @Transactional(readOnly = true)
	// @Override
	// public T selectByPrimaryKey(ID id) {
	// return baseMapper.selectByPrimaryKey(id);
	// }
	//
	// @Override
	// public int updateByPrimaryKeySelective(T record) {
	// return baseMapper.updateByPrimaryKeySelective(record);
	// }
	//
	// @Override
	// public int updateByPrimaryKey(T record) {
	// return baseMapper.updateByPrimaryKey(record);
	// }
	//
	// @Override
	// public int deleteByPrimaryKey(ID id) {
	// return baseMapper.deleteByPrimaryKey(id);
	// }
	//
	// @Override
	// public List<T> selectAll() {
	// return baseMapper.selectAll();
	// }
	//
	// @Override
	// public List<T> selectListByParams(Map<String, Object> params) {
	// return baseMapper.selectListByParams(params);
	// }
	//
	// /**
	// * 通用简单列表分页功能实现,如果需要更加复杂的分页请直接SQL完成
	// */
	// @Override
	// public PageInfo<T> selectListByPage(int pageNum, int pageSize) {
	// PageHelper.startPage(pageNum, pageSize);
	// List<T> list = baseMapper.selectAll();
	// // 用PageInfo对结果进行包装
	// PageInfo<T> pageInfo = new PageInfo<T>(list);
	// return pageInfo;
	// }
	//
	// @Override
	// public PageInfo<T> selectListByPage(Map<String, Object> params) {
	// log.debug("selectListByPage query by params = " +
	// JsonUtils.toJson(params));
	// // 默认分页的大小
	// int pageNum = 1;
	// int pageSize = 10;
	// PageInfo<T> pageInfo = null;
	// if (params != null) {
	// if (params.get("pageNum") != null) {
	// pageNum = Integer.valueOf(params.get("pageNum").toString());
	// }
	// if (params.get("pageSize") != null) {
	// pageSize = Integer.valueOf(params.get("pageSize").toString());
	// }
	// PageHelper.startPage(pageNum, pageSize);
	// List<T> list = baseMapper.selectListByParams(params);
	// // 用PageInfo对结果进行包装
	// pageInfo = new PageInfo<T>(list);
	// }
	// return pageInfo;
	// }
	//
	// @Override
	// public List<Map<String, Object>> queryListMapByConditions(Map<String,
	// Object> params) throws BusinessException {
	// return baseMapper.queryListMapByConditions(params);
	// }

}
