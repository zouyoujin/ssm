package com.ssm.datasource.common;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.ssm.common.exception.BusinessException;

public interface BaseBusiness<T, ID extends Serializable> {

	int insertByMethod(String statement, T record) throws BusinessException;
	
	int insert(T record);

	int insertSelective(T record);

	T selectByPrimaryKey(ID id);

	int updateByPrimaryKeySelective(T record);

	int updateByPrimaryKey(T record);
	
	int deleteByPrimaryKey(ID id);
	
	List<T> selectAll();
	
	List<T> selectListByParams(Map<String,Object> params);
	
	PageInfo<T> selectListByPage(int pageNum,int pageSize);
	
	PageInfo<T> selectListByPage(Map<String,Object> params);
	
	/**
	 * 根据查询条件返回动态数据
	 * @return 
	 * @throws BusinessException
	 */
	List<Map<String, Object>> queryListMapByConditions(Map<String,Object> params) throws BusinessException;
}
