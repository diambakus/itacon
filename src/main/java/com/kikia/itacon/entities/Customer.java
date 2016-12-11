package com.kikia.itacon.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Customer implements Serializable{
     
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8127033299523645919L;
	private Long customer_id;
	private String firstName;
	private String lastName;
	private String BI;
	private BigDecimal balance;
	private Set<String> phones = new HashSet<String>(0);
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(Long customer_id) {
		this.customer_id = customer_id;
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
	@ElementCollection
	public Set<String> getPhones() {
		return phones;
	}
	public void setPhones(Set<String> phones) {
		this.phones = phones;
	} 
}
