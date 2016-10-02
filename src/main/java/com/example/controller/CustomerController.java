package com.example.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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
import com.example.exception.ValidateException;
import com.example.response.ErrorDetail;
import com.example.response.ErrorResponse;

@RestController
@RequestMapping(value = "customers")
public class CustomerController {

	@Autowired
	CustomerService customerService;

	@GetMapping
	public Page<Customer> getCustomers(@PageableDefault Pageable pageable) {
		return customerService.findAll(pageable);
	}

	@GetMapping(path = "{id}")
	public Customer getCustomer(@PathVariable Integer id) {
		return customerService.findOne(id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void createCustomer(final @Valid @RequestBody Customer customer, final BindingResult bindingResult)
			throws ValidateException {
		if (bindingResult.hasErrors()) {
			throw new ValidateException(createErrorDetail(bindingResult));
		}
		customerService.create(customer);
	}

	@PutMapping(path = "{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public void updateCustomer(@PathVariable Integer id, @Valid @RequestBody Customer customer,
			final BindingResult bindingResult) throws ValidateException {
		if (bindingResult.hasErrors()) {
			throw new ValidateException(createErrorDetail(bindingResult));
		}
		customer.setId(id);
		customerService.update(customer);
	}

	@DeleteMapping(path = "{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteCustomer(@PathVariable Integer id) {
		customerService.delete(id);
	}

	private ErrorResponse createErrorDetail(final BindingResult bindingResult) {
		List<FieldError> fieldErrors = bindingResult.getFieldErrors();
		ErrorResponse errorResponse = new ErrorResponse();
		List<ErrorDetail> detailList = errorResponse.getDetail();
		for (FieldError fe : fieldErrors) {
			ErrorDetail errorDetail = new ErrorDetail();
			errorDetail.setProperty(fe.getField());
			errorDetail.setMessage(fe.getDefaultMessage());
			detailList.add(errorDetail);
		}
		return errorResponse;
	}
}