package com.example.aop;

import java.util.HashMap;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.enums.ErrorSet;

/**
 * 例外発生時のAdviceをまとめたAspectクラスです。
 * 
 * @author NK
 *
 */
@ControllerAdvice
public class ExceptionHandlerAspect {

	/**
	 * 許可されていないHTTPメソッドのエラーハンドリング
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
				this.put("code", ErrorSet.METHOD_NOT_ALLOWED.getCode());
				this.put("message", ErrorSet.METHOD_NOT_ALLOWED.getMessage());
			}
		};
	}
	
	/**
	 * 既にリソースが削除されている際のエラーハンドリング
	 * 
	 * @return エラーレスポンス
	 */
	@SuppressWarnings("serial")
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({ EmptyResultDataAccessException.class })
	@ResponseBody
	public HashMap<String, Object> handleResourceAlreadyDeleted() {
		return new HashMap<String, Object>() {
			{
				this.put("code", ErrorSet.RESOURCE_ALREADY_DELETED.getCode());
				this.put("message", ErrorSet.RESOURCE_ALREADY_DELETED.getMessage());
			}
		};
	}
}
