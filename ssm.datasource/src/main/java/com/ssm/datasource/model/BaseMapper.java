package com.ssm.datasource.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 基于Mybatis的基础泛型DAO实现类。 
 * @author Kitty
 *
 * @param <T> 业务实体类型 
 * @param <ID> ID类型 ，如：String、Long、Integer 等
 */
public interface BaseMapper<T, ID extends Serializable> {

	public int deleteByPrimaryKey(ID id);

	public int insert(T record);

	public int insertSelective(T record);

	public T selectByPrimaryKey(ID id);

	public int updateByPrimaryKeySelective(T record);

	public int updateByPrimaryKey(T record);
	
	public List<T> selectAll();
	
	public List<T> selectListByParams(Map<String,Object> params);
	
	public List<Map<String, Object>> queryListMapByConditions(Map<String, Object> params);
}