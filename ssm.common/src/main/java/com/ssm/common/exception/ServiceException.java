package com.ssm.common.exception;

import com.ssm.common.support.HttpCode;

/**
 * 系统业务异常信息
 * @author Kitty
 *
 */
public class ServiceException extends BaseException {

	private static final long serialVersionUID = 7649778455444131608L;

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}

	protected HttpCode getCode() {
		return HttpCode.CONFLICT;
	}
}
