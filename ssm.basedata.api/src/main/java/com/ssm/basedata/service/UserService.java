package com.ssm.basedata.service;

import com.ssm.common.exception.BusinessException;
import com.ssm.common.model.User;
import com.ssm.common.pojo.UserTO;

/**
 * 用户服务类接口
 * 
 * @author kitty
 */
public interface UserService {

	/**
	 * 根据用户ID查询用户数据
	 * 
	 * @param id
	 * @return
	 */
	public User getUserById(Long id) throws BusinessException;

	/**
	 * 用户注册处理
	 * 
	 * @param userTO
	 * @return
	 */
	public User registerUser(UserTO userTO) throws BusinessException;
	
	/**
	 * 清空用户相关缓存信息
	 * @param userTO
	 * @return 成功:true 失败:false
	 */
	public Boolean clearAllCache() throws BusinessException;
}
