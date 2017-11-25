package com.kikia.itacon.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotEmpty;

public class InstitutionDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2870360329026877028L;
	private Long id = null;
	@NotEmpty
	private String name = "";
	private String code;
	private int numberOfInstitutions;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}	

	public int getNumberOfInstitutions() {
		return numberOfInstitutions;
	}

	public void setNumberOfInstitutions(int numberOfInstitutions) {
		this.numberOfInstitutions = numberOfInstitutions;
	}
}
