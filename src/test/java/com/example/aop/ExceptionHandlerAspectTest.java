package com.example.aop;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;

import org.junit.Test;
import org.springframework.http.HttpStatus;

import com.example.TestCaseBase;
import com.example.domain.entity.Customer;
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
	public void testHandleValidationError() {
		
		Customer customer = new Customer();
		customer.setName("テストテストテストテストテストテストテスト");
		customer.setZipCode("12345678");
		customer.setAddress("テストテストテストテストテストテストテストテストテストテストテストテストテストテストテストテストテストテストテストテストテストテストテストテストテストテストテストテストテストテストテストテストテストテストテスト"
				+ "テストテストテストテストテストテストテストテストテストテストテストテストテストテストテストテストテストテストテストテストテスト");
		customer.setPhoneNumber("0909999001001");
		customer.setSex("2");
		
		given().body(customer)
			.contentType(ContentType.JSON)
			.and()
			.when().post("/customers")
			.then()
			.statusCode(HttpStatus.BAD_REQUEST.value())
			.contentType(ContentType.JSON)
			.body("message", is(ErrorSet.PARAMETER_INVALID.getMessage()))
			.body("code", is(ErrorSet.PARAMETER_INVALID.getCode()))
			.body("detail.property", hasItems("name", "zipCode", "address", "phoneNumber", "sex"));
			//.body("detail.message", hasItems("0または1に設定してください。, 半角数字7桁に設定してください。, 1文字以上100文字以内に設定してください。, 1文字以上20文字以内に設定してください。, 半角数字1文字以上12文字以内に設定してください。"));
		
		// see https://github.com/rest-assured/rest-assured/wiki/Usage#example-1---json
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
