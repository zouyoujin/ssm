package com.ssm.basedata.model.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssm.common.model.User;
import com.ssm.datasource.model.BaseMapper;

@Repository
public interface UserMapper extends BaseMapper<User, Long>{
	
	/**
	 * 批量添加数据
	 * @param userList
	 * @return
	 */
	public int batchInsertRecord(List<User> userList);
	
	/**
	 * 批量更新数据
	 * @param userList
	 * @return
	 */
	public int batchUpdateRecord(List<User> userList);
}