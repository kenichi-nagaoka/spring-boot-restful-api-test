package com.example.controller;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.is;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.domain.entity.Customer;
import com.example.domain.repository.CustomerRepository;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerControllerTest {

	@LocalServerPort
	int port;

	@Autowired
	CustomerRepository customerRepository;

	Customer customer1;
	Customer customer2;

	@Before
	public void setUp() {

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
		RestAssured.port = this.port;
	}

	@Test
	public void testGetCustomers() {
		when().get("/customers")
			.then()
			.body("contents.size()", is(2))
			.statusCode(HttpStatus.OK.value())
			.body("id[0]", is(customer1.getId()))
			.body("name[0]", is(customer1.getName()))
			.body("zipCode[0]", is(customer1.getZipCode()))
			.body("address[0]", is(customer1.getAddress()))
			.body("phoneNumber[0]", is(customer1.getPhoneNumber()))
			.body("sex[0]", is(customer1.getSex()))
			.body("id[1]", is(customer2.getId()))
			.body("name[1]", is(customer2.getName()))
			.body("zipCode[1]", is(customer2.getZipCode()))
			.body("address[1]", is(customer2.getAddress()))
			.body("phoneNumber[1]", is(customer2.getPhoneNumber()))
			.body("sex[1]", is(customer2.getSex()));
	}
	
	@Test
	public void testCreateCustomer() {
		
		Customer customer3 = new Customer();
		customer3.setName("FugaTaro");
		customer3.setZipCode("1000002");
		customer3.setAddress("東京都港区");
		customer3.setPhoneNumber("09000000002");
		customer3.setSex("0");
		
		given().body(customer3)
			.contentType(ContentType.JSON)
			.and()
			.when().post("/customers")
			.then()
			.statusCode(HttpStatus.CREATED.value());
			//.body(); ResponseBodyが空の検証を追加する
			
		when().get("/customers")
			.then()
			.body("contents.size()", is(3));
	}
}
