package com.ssm.basedata.business.user.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssm.basedata.business.user.UserBusiness;
import com.ssm.basedata.model.mapper.UserMapper;
import com.ssm.common.model.User;
import com.ssm.common.pojo.UserTO;
import com.ssm.datasource.common.BaseBusinessImpl;

/**
 * 
 * 用户业务实现类处理
 * 
 * @author Kitty
 *
 */
@Service("userBusinessImpl")
@Transactional(rollbackFor = { Exception.class })
public class UserBusinessImpl extends BaseBusinessImpl<User, Long> implements UserBusiness {

	private static final Logger logger = LoggerFactory.getLogger(UserBusinessImpl.class);

	@Resource
	private UserMapper userMapper;
	
	@Autowired
	public void setBaseMapper(UserMapper userMapper) {
		super.setBaseMapper(userMapper);
	}

	@Transactional(readOnly = true)
	@Cacheable(cacheNames="users",keyGenerator="cacheKeyGenerator")
	@Override
	public User getUserById(Long id) {
		logger.info("database getUserById id = " + id);
		return userMapper.selectByPrimaryKey(id);
	}

	@Override
	public User registerUser(UserTO userTO) {
		User user = new User(userTO);
		Date now = new Date();
		user.setCreateTime(now);
		user.setUpdateTime(now);
		userMapper.insert(user);
		return user;
	}
}
