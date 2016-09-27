package com.exapmle;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.domain.repository.CustomerRepository;

import io.restassured.RestAssured;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestCaseBase {

	@LocalServerPort
	protected int port;

	@Autowired
	protected CustomerRepository customerRepository;
	
	protected void setUpDetail() {}
	
	@Before
	public void setUp() {
		RestAssured.port = this.port;
		setUpDetail();
	}
}
