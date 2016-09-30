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
		customerRepository.deleteAll();
	}
	
	@Test
	public void testHandleMethodTypeError() {
		
		when().patch("/customers")
			.then()
			.statusCode(HttpStatus.METHOD_NOT_ALLOWED.value())
			.contentType(ContentType.JSON)
			.body("message", is(ErrorSet.METHOD_NOT_ALLOWED.getMessage()))
			.body("code", is(ErrorSet.METHOD_NOT_ALLOWED.getCode()));
	}
	
	@Test
	public void testHandleResourceAlreadyDeleted() {
		
		when().delete("/customers/1")
			.then()
			.statusCode(HttpStatus.BAD_REQUEST.value())
			.contentType(ContentType.JSON)
			.body("message", is(ErrorSet.RESOURCE_ALREADY_DELETED.getMessage()))
			.body("code", is(ErrorSet.RESOURCE_ALREADY_DELETED.getCode()));
	}
}
