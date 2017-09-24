package com.ssm.basedata.business.user;

import com.ssm.common.model.User;
import com.ssm.common.pojo.UserTO;
import com.ssm.datasource.common.BaseBusiness;

/**
 * 
 * 用户业务处理实现类
 * @author Kitty
 *
 */
public interface UserBusiness extends BaseBusiness<User, Long>{

	/**
	 * 根据用户ID查询用户数据
	 * @param id
	 * @return
	 */
	public User getUserById(Long id);
	
	/**
	 * 用户注册处理
	 * @param userTO
	 * @return
	 */
	public User registerUser(UserTO userTO);
	
	/**
	 * 清空用户相关缓存信息
	 * @return
	 */
	public Boolean clearAllCache();
}
