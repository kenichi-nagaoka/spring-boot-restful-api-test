package com.example.exception;

import com.example.response.ErrorResponse;

public class ValidateException extends Exception {

	private static final long serialVersionUID = 1L;

	private ErrorResponse errorResponse;

	public ValidateException(ErrorResponse errorResponse) {
		this.setErrorResponse(errorResponse);
	}

	public ErrorResponse getErrorResponse() {
		return errorResponse;
	}

	public void setErrorResponse(ErrorResponse errorResponse) {
		this.errorResponse = errorResponse;
	}
}
