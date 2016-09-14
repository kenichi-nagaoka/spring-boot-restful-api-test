package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootResTfulApiTestSampleApplicationTest {

	@Test
	public void contextLoads() {
		SpringBootResTfulApiTestSampleApplication s = new SpringBootResTfulApiTestSampleApplication();
		s.main(null);
	}

}
