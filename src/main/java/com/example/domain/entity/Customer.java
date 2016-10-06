package com.example.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Customer")
public class Customer {
	
	/*
	 * JPAの仕様上、引数なしのデフォルトコンストラクタが必要
	 */
	public Customer() {
	}

	@Id
	@Column
	@GeneratedValue
	private Integer id;

	@Column
	@NotNull
	@Size(min = 1, max = 20, message="{Customer.name.invalid}")
	private String name;

	@Column
	@NotNull
	@Pattern(regexp = "\\d{7}", message = "{Customer.zipCode.invalid}")
	private String zipCode;

	@Column
	@NotNull
	@Size(min = 1, max = 100, message = "{Customer.address.invalid}")
	private String address;

	@Column
	@NotNull
	@Pattern(regexp = "\\d{1,12}", message = "{Customer.phoneNumber.invalid}")
	private String phoneNumber;

	@Column
	@NotNull
	@Pattern(regexp = "[0-1]", message = "{Customer.sex.invalid}")
	private String sex;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
}