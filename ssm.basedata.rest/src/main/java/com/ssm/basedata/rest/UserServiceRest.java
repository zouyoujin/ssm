package com.ssm.basedata.rest;

import com.ssm.common.model.User;
import com.ssm.common.pojo.ResponseService;
import com.ssm.common.pojo.UserTO;

/**
 * 用户接口模块
 * @author Kitty
 *
 */
public interface UserServiceRest {

    /**
     * 根据用户id获取用户信息
     * @param id 用户ID
     * @return 用户信息	
     */
	ResponseService<User> getUser(Long id);

	/**
	 * 用户注册接口
	 * @param userTO
	 * @return 用户ID
	 */
	ResponseService<Long> registerUser(UserTO userTO);
	
	/**
	 * 清空用户相关缓存信息
	 * @param userTO
	 * @return 成功:true 失败:false
	 */
	ResponseService<Boolean> clearAllCache();
}
