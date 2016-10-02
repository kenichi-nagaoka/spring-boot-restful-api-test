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
	@Size(min = 1, max = 20, message = "1文字以上20文字以内に設定してください。")
	//@Size(min = 1, max = 20, message = "{invalid.customer.name}")
	private String name;

	@Column
	@NotNull
	@Pattern(regexp = "\\d{7}", message = "半角数字7桁に設定してください。")
	private String zipCode;

	@Column
	@NotNull
	@Size(min = 1, max = 100, message = "1文字以上100文字以内に設定してください。")
	private String address;

	@Column
	@NotNull
	@Pattern(regexp = "\\d{1,12}", message = "半角数字1文字以上12文字以内に設定してください。")
	private String phoneNumber;

	@Column
	@NotNull
	@Pattern(regexp = "[0-1]", message = "0または1を設定してください。")
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