package com.ssm.common.exception;

import com.ssm.common.support.HttpCode;

/**
 * 系统业务异常信息
 * @author Kitty
 *
 */
public class BusinessException extends BaseException {

	private static final long serialVersionUID = 7649778455444131608L;

	public BusinessException(String message) {
		super(message);
	}

	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	public BusinessException(Throwable cause) {
		super(cause);
	}

	protected HttpCode getCode() {
		return HttpCode.CONFLICT;
	}
}
