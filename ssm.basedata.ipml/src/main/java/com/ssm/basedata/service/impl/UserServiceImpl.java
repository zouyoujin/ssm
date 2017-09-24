package com.ssm.basedata.service.impl;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ssm.basedata.business.user.UserBusiness;
import com.ssm.basedata.service.UserService;
import com.ssm.common.exception.BusinessException;
import com.ssm.common.model.User;
import com.ssm.common.pojo.UserTO;

/**
 * 
 * 用户服务接口实现类
 * @author Kitty
 *
 */
@Service("userServiceImpl")
public class UserServiceImpl implements UserService{
	
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Resource(name="userBusinessImpl")
	private UserBusiness userBusiness;

	@Override
	public User getUserById(Long id) throws BusinessException{
//		User user = new User(id, "username" + id, "123456");
//		//userBusiness.insertByMethod("com.yyc.portal.model.mapper.UserMapper.insert", user);
//		
//		Map<String, Object> queryParams = new HashMap<String, Object>();
//		String[] columns = new String[]{"id","username"};
//		queryParams.put("columnNames", StringUtils.join(columns, ","));
//		queryParams.put("tableName", "tbl_user");
//		
//		List<Map<String, Object>> filters = new ArrayList<Map<String,Object>>();
//		
//		Map<String, Object> nameFilter = new HashMap<String, Object>();
//		nameFilter.put("fieldCat", "and");
//		nameFilter.put("fieldName", "id");
//		nameFilter.put("fieldType", "STRING");
//		nameFilter.put("fieldValue", new Long[]{19L, 20L});
//		//nameFilter.put("opt", "LIKE");
//		nameFilter.put("opt", "IN");
//		filters.add(nameFilter); 
//		
//		Map<String, Object> eqFilter = new HashMap<String, Object>();
//		nameFilter.put("fieldCat", "or");
//		eqFilter.put("fieldName", "id");
//		eqFilter.put("fieldType", "STRING");
//		eqFilter.put("fieldValue", new Long[]{21L});
//		eqFilter.put("opt", "IN");
//		filters.add(eqFilter);
//		
//		queryParams.put("filters", filters);
//		
//		List<Map<String,Object>> userMaps = userBusiness.queryListMapByConditions(queryParams);
//		
//		logger.info(JsonUtils.toJson(userMaps));
		
		User user = userBusiness.getUserById(id);
		return user;
	}

	@Override
	public User registerUser(UserTO userTO) throws BusinessException{
		if (userTO == null) {
			logger.error("registerUser,but userTO is null.");
			throw new BusinessException("registerUser,but userTO is null.");
		}
		if (StringUtils.isEmpty(userTO.getUsername())
				|| StringUtils.isEmpty(userTO.getPassword())) {
			logger.error("registerUser,but username is null or password is null.");
			throw new BusinessException(
					"registerUser,but username is null or password is null.");
		}
		User user = userBusiness.registerUser(userTO);
		return user;
	}

	@Override
	public Boolean clearAllCache() throws BusinessException {
		return userBusiness.clearAllCache();
	}

}
