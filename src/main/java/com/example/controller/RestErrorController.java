package com.example.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.enums.ErrorSet;

/**
 * /errorにマッピングされたコントローラークラスです。
 * 
 * @author NK
 *
 */
@RestController
public class RestErrorController implements ErrorController {

	/**
	 * リソースが存在しない場合のエラーハンドリング
	 * 
	 * @return エラーレスポンス
	 */
	@SuppressWarnings("serial")
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@GetMapping(path = "error")
	@ResponseBody
	public HashMap<String, Object> handleNotFoundError(HttpServletRequest request, HttpServletResponse response) {
		return new HashMap<String, Object>() {
			{
				this.put("code", ErrorSet.NOT_FOUNT.getCode());
				this.put("message", ErrorSet.NOT_FOUNT.getMessage());
			}
		};
	}

	@Override
	public String getErrorPath() {
		return "/error";
	}
}
