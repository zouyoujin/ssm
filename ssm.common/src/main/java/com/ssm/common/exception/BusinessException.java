package com.ssm.common.exception;

public class BusinessException extends RuntimeException {

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

}
