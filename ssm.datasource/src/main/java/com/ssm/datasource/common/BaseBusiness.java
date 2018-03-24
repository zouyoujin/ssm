package com.ssm.datasource.common;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.ssm.common.model.BaseModel;

public interface BaseBusiness<T extends BaseModel>{
	
	public T update(T record);

	public void del(List<Long> ids, Long userId);

	public void del(Long id, Long userId);

	public void delete(Long id);

	public Integer deleteByEntity(T t);

	public Integer deleteByMap(Map<String, Object> columnMap);

	public T queryById(Long id);

	public Page<T> query(Map<String, Object> params);

	public List<T> queryList(Map<String, Object> params);

	public Page<T> query(T params, Pagination pagination);

	public List<T> getList(T params);

	public Page<Map<String, Object>> getPageMap(final Page<Long> ids);

	public List<T> getListByIds(List<Long> ids);

	public <K> List<K> getListByIds(List<Long> ids, Class<K> cls);

	public List<T> selectList(Wrapper<T> entity);

	public T selectOne(T entity);

	public T updateAllColumn(T record);

	public boolean updateAllColumnBatch(List<T> entityList);

	public boolean updateAllColumnBatch(List<T> entityList, int batchSize);

	public boolean updateBatch(List<T> entityList);

	public boolean updateBatch(List<T> entityList, int batchSize);
	

//	int insertByMethod(String statement, T record) throws BusinessException;
//	
//	int insert(T record);
//
//	int insertSelective(T record);
//
//	T selectByPrimaryKey(ID id);
//
//	int updateByPrimaryKeySelective(T record);
//
//	int updateByPrimaryKey(T record);
//	
//	int deleteByPrimaryKey(ID id);
//	
//	List<T> selectAll();
//	
//	List<T> selectListByParams(Map<String,Object> params);
//	
//	PageInfo<T> selectListByPage(int pageNum,int pageSize);
//	
//	PageInfo<T> selectListByPage(Map<String,Object> params);
//	
//	/**
//	 * 根据查询条件返回动态数据
//	 * @return 
//	 * @throws BusinessException
//	 */
//	List<Map<String, Object>> queryListMapByConditions(Map<String,Object> params) throws BusinessException;
}
