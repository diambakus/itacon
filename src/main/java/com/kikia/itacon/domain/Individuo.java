package com.kikia.itacon.domain;

import javax.persistence.Entity;

import org.apache.commons.lang3.builder.HashCodeBuilder;

@Entity
public class Individuo extends Contribuinte {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String firstName;
	private String lastName;
	private String BI;
	private Long NIF;

	public Individuo() {
		// TODO Auto-generated constructor stub
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


	public Long getNIF() {
		return NIF;
	}

	public void setNIF(Long NIF) {
		this.NIF = NIF;
	}

	@Override
	public int hashCode() {
		final int prime = 51;
		return new HashCodeBuilder(prime, 17).append(BI).append(NIF).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Individuo))
			return false;
		Individuo other = (Individuo) obj;
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
