package com.ssm.datasource.common;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssm.common.exception.BusinessException;
import com.ssm.common.utils.JsonUtils;
import com.ssm.datasource.model.BaseMapper;

@Service
@Transactional
public class BaseBusinessImpl<T, ID extends Serializable> implements BaseBusiness<T, ID> {

	private static final Logger log = LoggerFactory.getLogger(BaseBusinessImpl.class);

	@Resource
	public SqlSessionTemplate sqlSessionTemplate;

	private BaseMapper<T, ID> baseMapper;

	public void setBaseMapper(BaseMapper<T, ID> baseMapper) {
		this.baseMapper = baseMapper;
	}

	@Override
	public int insertByMethod(String statement, T record) throws BusinessException {
		return sqlSessionTemplate.insert(statement, record);
	}

	@Override
	public int insert(T record) {
		return baseMapper.insert(record);
	}

	@Override
	public int insertSelective(T record) {
		return baseMapper.insertSelective(record);
	}

	@Transactional(readOnly = true)
	@Override
	public T selectByPrimaryKey(ID id) {
		return baseMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(T record) {
		return baseMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(T record) {
		return baseMapper.updateByPrimaryKey(record);
	}

	@Override
	public int deleteByPrimaryKey(ID id) {
		return baseMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<T> selectAll() {
		return baseMapper.selectAll();
	}

	@Override
	public List<T> selectListByParams(Map<String, Object> params) {
		return baseMapper.selectListByParams(params);
	}

	/**
	 * 通用简单列表分页功能实现,如果需要更加复杂的分页请直接SQL完成
	 */
	@Override
	public PageInfo<T> selectListByPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<T> list = baseMapper.selectAll();
		// 用PageInfo对结果进行包装
		PageInfo<T> pageInfo = new PageInfo<T>(list);
		return pageInfo;
	}

	@Override
	public PageInfo<T> selectListByPage(Map<String, Object> params) {
		log.debug("selectListByPage query by params = " + JsonUtils.toJson(params));
		// 默认分页的大小
		int pageNum = 1;
		int pageSize = 10;
		PageInfo<T> pageInfo = null;
		if (params != null) {
			if (params.get("pageNum") != null) {
				pageNum = Integer.valueOf(params.get("pageNum").toString());
			}
			if (params.get("pageSize") != null) {
				pageSize = Integer.valueOf(params.get("pageSize").toString());
			}
			PageHelper.startPage(pageNum, pageSize);
			List<T> list = baseMapper.selectListByParams(params);
			// 用PageInfo对结果进行包装
			pageInfo = new PageInfo<T>(list);
		}
		return pageInfo;
	}

	@Override
	public List<Map<String, Object>> queryListMapByConditions(Map<String, Object> params) throws BusinessException {
		return baseMapper.queryListMapByConditions(params);
	}

}
