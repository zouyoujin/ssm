package com.ssm.basedata.business.user.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
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
@CacheConfig(cacheNames = "users")
public class UserBusinessImpl extends BaseBusinessImpl<User> implements UserBusiness {

	private static final Logger logger = LoggerFactory.getLogger(UserBusinessImpl.class);

	@Cacheable(cacheNames = "users", keyGenerator = "cacheKeyGenerator")
	@Transactional(transactionManager = "transactionManager", readOnly = true)
	@Override
	public User getUserById(Long id) {
		logger.info("database getUserById id = " + id);
		return ((UserMapper) mapper).selectById(id);
	}

	@CachePut(cacheNames = "users", key = "#userTO.getUsername()")
	@Transactional(transactionManager = "transactionManager", rollbackFor = { Exception.class })
	@Override
	public User registerUser(UserTO userTO) {
		//TO->Model
		User user = new User();
		user.setAccount(userTO.getUsername());
		
		((UserMapper) mapper).insert(user);
		return user;
	}

	// 清空users缓存
	@CacheEvict(value = "users", allEntries = true)
	@Override
	public Boolean clearAllCache() {
		return true;
	}
}
