package com.example.aop;

import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.is;

import org.junit.Test;
import org.springframework.http.HttpStatus;

import com.example.TestCaseBase;
import com.example.enums.ErrorSet;

import io.restassured.http.ContentType;

public class ExceptionHandlerAspectTest extends TestCaseBase {

	@Override
	public void setUpDetail() {
	}

	@Test
	public void testandleMethodTypeError() {
		
		when().patch("/customers")
			.then()
			.statusCode(HttpStatus.METHOD_NOT_ALLOWED.value())
			.contentType(ContentType.JSON)
			.body("message", is(ErrorSet.METHOD_TYPE_ERROR.getMessage()))
			.body("code", is(ErrorSet.METHOD_TYPE_ERROR.getCode()));
	}
}
