package com.example.enums;

public enum ErrorSet {

	METHOD_TYPE_ERROR("9001", "許可されていないメソッドタイプです。");

	private final String code;

	private final String message;

	ErrorSet(final String code, final String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
}
