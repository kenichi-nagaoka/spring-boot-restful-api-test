package com.example.enums;

public enum ErrorSet {

	NOT_FOUNT("9000", "リソースが存在しません。URIが間違っている可能性があります。"),
	METHOD_NOT_ALLOWED("9001", "許可されていないメソッドタイプです。"),
	RESOURCE_ALREADY_DELETED("9002", "既にリソースは削除されています。");

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
