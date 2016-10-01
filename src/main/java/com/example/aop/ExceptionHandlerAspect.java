package com.example.aop;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.enums.ErrorSet;
import com.example.exception.ValidateException;
import com.example.response.ErrorResponse;

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
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	@ExceptionHandler({ HttpRequestMethodNotSupportedException.class })
	@ResponseBody
	public ErrorResponse handleMethodTypeError() {
		return createErrorResponse(new ErrorResponse(), ErrorSet.METHOD_NOT_ALLOWED);
	}

	/**
	 * バリデーションエラーハンドリング
	 * 
	 * @return エラーレスポンス
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({ ValidateException.class })
	@ResponseBody
	public ErrorResponse handleValidationError(ValidateException e) {
		return createErrorResponse(e.getErrorResponse(), ErrorSet.PARAMETER_INVALID);
	}

	/**
	 * 既にリソースが削除されている際のエラーハンドリング
	 * 
	 * @return エラーレスポンス
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({ EmptyResultDataAccessException.class })
	@ResponseBody
	public ErrorResponse handleResourceAlreadyDeleted() {
		return createErrorResponse(new ErrorResponse(), ErrorSet.RESOURCE_ALREADY_DELETED);
	}

	private ErrorResponse createErrorResponse(ErrorResponse errorResponse, ErrorSet errorSet) {
		errorResponse.setCode(errorSet.getCode());
		errorResponse.setMessage(errorSet.getMessage());
		return errorResponse;
	}
}