package com.kikia.itacon.entities;

import java.math.BigDecimal;

import javax.persistence.Entity;

@Entity
public class Customer extends BasicEntity {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String firstName;
	private String lastName;
	private String BI;
	private BigDecimal balance;
	private String phone;


	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getBI() {
		return BI;
	}

	public void setBI(String bI) {
		BI = bI;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	//@ElementCollection
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public Customer() {
		firstName = null;
		lastName = null;
		BI = null;
		balance = new BigDecimal("0.0");
		phone = null;
	}
}
