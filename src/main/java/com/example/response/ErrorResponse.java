package com.example.response;

import java.util.ArrayList;
import java.util.List;

public class ErrorResponse {

	private String code;

	private String message;

	private List<ErrorDetail> detail = new ArrayList<ErrorDetail>();

	public List<ErrorDetail> getDetail() {
		return detail;
	}

	public void setDetail(List<ErrorDetail> detail) {
		this.detail = detail;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
