package com.example.controller;

import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.is;

import org.junit.Test;
import org.springframework.http.HttpStatus;

import com.example.TestCaseBase;
import com.example.enums.ErrorSet;

import io.restassured.http.ContentType;

public class RestErrorControllerTest extends TestCaseBase {

	@Test
	public void testHandleNotFoundError() {
		when().get("/customersss")
			.then()
			.statusCode(HttpStatus.NOT_FOUND.value())
			.contentType(ContentType.JSON)
			.body("message", is(ErrorSet.NOT_FOUNT.getMessage()))
			.body("code", is(ErrorSet.NOT_FOUNT.getCode()));
	}
}
