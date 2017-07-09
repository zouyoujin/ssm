package com.ssm.basedata.rest;

import javax.validation.constraints.Min;

import com.ssm.common.model.User;
import com.ssm.common.pojo.RegistUserResult;
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
	ResponseService<User> getUser(@Min(value=1L, message="User ID must be greater than 1") Long id);

	/**
	 * 用户注册接口
	 * @param userTO
	 * @return
	 */
	ResponseService<RegistUserResult> registerUser(UserTO userTO);
}
