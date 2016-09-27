package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.entity.Customer;
import com.example.domain.service.CustomerService;

@RestController
@RequestMapping(value = "customers")
public class CustomerController {

	@Autowired
	CustomerService customerService;

	@GetMapping
	public List<Customer> getCustomers() {
		return customerService.findAll();
	}

	@GetMapping(path = "{id}")
	public Customer getCustomer(@PathVariable Integer id) {
		return customerService.findOne(id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void createCustomer(@RequestBody Customer customer) {
		customerService.create(customer);
	}

	@PutMapping(path = "{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public void updateCustomer(@PathVariable Integer id, @RequestBody Customer customer) {
		customer.setId(id);
		customerService.update(customer);
	}

	@DeleteMapping(path = "{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteCustomer(@PathVariable Integer id) {
		customerService.delete(id);
	}
}