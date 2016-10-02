package com.example.controller;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;

import org.junit.Test;
import org.springframework.http.HttpStatus;

import com.example.TestCaseBase;
import com.example.domain.entity.Customer;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CustomerControllerTest extends TestCaseBase {

	private Customer customer1;
	private Customer customer2;

	@Override
	public void setUpDetail() {

		customerRepository.deleteAll();

		customer1 = new Customer();
		customer1.setName("HogeTaro");
		customer1.setZipCode("1000000");
		customer1.setAddress("東京都新宿区");
		customer1.setPhoneNumber("09000000000");
		customer1.setSex("0");

		customer2 = new Customer();
		customer2.setName("HogeHanako");
		customer2.setZipCode("1000001");
		customer2.setAddress("東京都渋谷区");
		customer2.setPhoneNumber("09000000001");
		customer2.setSex("1");

		customerRepository.save(Arrays.asList(customer1, customer2));
	}

	@Test
	public void testGetCustomers() {
		when().get("/customers")
			.then()
//			.body("contents.size()", is(2))
			.body("numberOfElements", is(2))
			.statusCode(HttpStatus.OK.value())
//			.body("id[0]", is(customer1.getId()))
//			.body("name[0]", is(customer1.getName()))
//			.body("zipCode[0]", is(customer1.getZipCode()))
//			.body("address[0]", is(customer1.getAddress()))
//			.body("phoneNumber[0]", is(customer1.getPhoneNumber()))
//			.body("sex[0]", is(customer1.getSex()))
//			.body("id[1]", is(customer2.getId()))
//			.body("name[1]", is(customer2.getName()))
//			.body("zipCode[1]", is(customer2.getZipCode()))
//			.body("address[1]", is(customer2.getAddress()))
//			.body("phoneNumber[1]", is(customer2.getPhoneNumber()))
//			.body("sex[1]", is(customer2.getSex()));
			.body("content[0].id", is(customer1.getId()))
			.body("content[0].name", is(customer1.getName()))
			.body("content[0].zipCode", is(customer1.getZipCode()))
			.body("content[0].address", is(customer1.getAddress()))
			.body("content[0].phoneNumber", is(customer1.getPhoneNumber()))
			.body("content[0].sex", is(customer1.getSex()))
			.body("content[1].id", is(customer2.getId()))
			.body("content[1].name", is(customer2.getName()))
			.body("content[1].zipCode", is(customer2.getZipCode()))
			.body("content[1].address", is(customer2.getAddress()))
			.body("content[1].phoneNumber", is(customer2.getPhoneNumber()))
			.body("content[1].sex", is(customer2.getSex()));
	}
	
	@Test
	public void testGetCustomer() {
		assertGetCustomer();
	}
	
	@Test
	public void testCreateCustomer() {
		
		Customer customer3 = new Customer();
		customer3.setName("FugaTaro");
		customer3.setZipCode("1000002");
		customer3.setAddress("東京都港区");
		customer3.setPhoneNumber("09000000002");
		customer3.setSex("0");
		
		Response response = given().body(customer3)
								.contentType(ContentType.JSON)
								.and()
								.when().post("/customers")
								.then()
								.statusCode(HttpStatus.CREATED.value())
								.extract()
								.response();
		
		assertThat(response.getBody().asString(), is(""));
			
		when().get("/customers")
			.then()
			//.body("contents.size()", is(3));
			.body("numberOfElements", is(3));
	}
	
	@Test
	public void testUpdateCustomer() {

		customer1.setName("UpdateUser");
		customer1.setZipCode("1000003");
		customer1.setAddress("東京都練馬区");
		customer1.setPhoneNumber("09000000003");
		customer1.setSex("1");
		
		given().body(customer1)
			.contentType(ContentType.JSON)
			.and()
			.when().put("/customers/{id}", customer1.getId())
			.then()
			.statusCode(HttpStatus.CREATED.value());
		
		assertGetCustomer();
	}
	
	@Test
	public void testDeleteCustomer() {
		
		when().delete("customers/{id}", customer1.getId())
			.then()
			.statusCode(HttpStatus.NO_CONTENT.value());
		
		when().get("customers").then()
			.statusCode(HttpStatus.OK.value())
			//.body("contents.size()", is(1));
			.body("numberOfElements", is(1));
	}
	
	private void assertGetCustomer() {
		when().get("/customers/{id}", customer1.getId())
			.then()
			.statusCode(HttpStatus.OK.value())
			.body("id", is(customer1.getId()))
			.body("name", is(customer1.getName()))
			.body("zipCode", is(customer1.getZipCode()))
			.body("address", is(customer1.getAddress()))
			.body("phoneNumber", is(customer1.getPhoneNumber()))
			.body("sex", is(customer1.getSex()));
	}
}
