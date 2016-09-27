package com.example.aop;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.enums.ErrorSet;

@ControllerAdvice
public class ExceptionHandlerAspect {

	/**
	 * 許可されていないメソッドタイプのエラーハンドリング
	 * 
	 * @return エラーレスポンス
	 */
	@SuppressWarnings("serial")
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	@ExceptionHandler({ HttpRequestMethodNotSupportedException.class })
	@ResponseBody
	public HashMap<String, Object> handleMethodTypeError() {

		return new HashMap<String, Object>() {
			{
				this.put("message", ErrorSet.METHOD_TYPE_ERROR.getMessage());
				this.put("code", ErrorSet.METHOD_TYPE_ERROR.getCode());
			}
		};
	}
}
