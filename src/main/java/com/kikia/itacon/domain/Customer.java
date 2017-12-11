package com.kikia.itacon.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Customer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String firstName;
	private String lastName;
	private String BI;
	private BigDecimal balance;
	private String phone;
	private Long NIF;

	public void setId(Long id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

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

	// @ElementCollection
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Long getNIF() {
		return NIF;
	}

	public void setNIF(Long nIF) {
		NIF = nIF;
	}

	public Customer() {
		firstName = null;
		lastName = null;
		BI = null;
		balance = new BigDecimal("0.0");
		phone = null;
		NIF = 0L;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		return prime * (((NIF == null) && (BI == null)) ? 0 : (NIF.hashCode() + BI.hashCode()));
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Customer))
			return false;
		Customer other = (Customer) obj;
		if (BI == null) {
			if (other.BI != null)
				return false;
		} else if (!BI.equals(other.BI))
			return false;
		if (NIF == null) {
			if (other.NIF != null)
				return false;
		} else if (!NIF.equals(other.NIF))
			return false;
		return true;
	}
}
