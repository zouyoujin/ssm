package com.ssm.basedata.rest.impl;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.ssm.basedata.rest.UserServiceRest;
import com.ssm.basedata.service.UserService;
import com.ssm.common.constant.ErrorCode;
import com.ssm.common.model.User;
import com.ssm.common.pojo.RegistUserResult;
import com.ssm.common.pojo.ResponseService;
import com.ssm.common.pojo.UserTO;

/**
 * 用户Rest接口
 * 
 * @author Kitty
 *
 */
@Path("users")
@Produces({ ContentType.APPLICATION_JSON_UTF_8 })
@Consumes({ MediaType.APPLICATION_JSON })
@Named
public class UserServiceRestImpl implements UserServiceRest {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceRestImpl.class);

	@Inject
	private UserService userService;

	@GET
	@Path("{id:\\d+}")
	public ResponseService<User> getUser(@PathParam("id") Long id) {

		ResponseService<User> response = new ResponseService<User>();
		try {
			User user = userService.getUserById(id);
			response.setData(user);
		} catch (Exception e) {
			response.setStatus(ErrorCode.USER_SAVE_EXCEPTION);
			response.setMessage("UserRestServiceImpl=>getUser exception userId = [" + id + "]");
			logger.error("UserRestServiceImpl=>getUser exception userId = [" + id + "]" + e.getMessage(), e);
		}

		return response;
	}

	@POST
	@Path("register")
	public ResponseService<RegistUserResult> registerUser(UserTO userTO) {
		ResponseService<RegistUserResult> response = new ResponseService<RegistUserResult>();
		try {
			User user = userService.registerUser(userTO);
			response.setData(new RegistUserResult(user.getId()));
		} catch (Exception e) {
			response.setStatus(ErrorCode.USER_REGISTER_EXCEPTION);
			response.setMessage("UserRestServiceImpl=>registerUser exception userTO =[" + userTO.toString() + "]");
			logger.error(
					"UserRestServiceImpl=>registerUser exception userTO =[" + userTO.toString() + "]" + e.getMessage(),
					e);
		}
		return response;
	}
}
