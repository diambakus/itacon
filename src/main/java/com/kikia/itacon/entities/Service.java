package com.kikia.itacon.entities;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "service")
public class Service extends BasicEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5248273079563923850L;

	private String name;
	private String code;
	private BigDecimal price;
	private PublicInstitution publicInstitution;

	public Service(String name) {
		this.name = name;
	}

	public Service(String name, PublicInstitution publicInstitution) {
		this.publicInstitution = publicInstitution;
		this.name = name;
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

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@ManyToOne
	@JoinColumn(name = "public_service_id")
	public PublicInstitution getPublicInstitution() {
		return publicInstitution;
	}

	public void setPublicInstitution(PublicInstitution publicInstitution) {
		this.publicInstitution = publicInstitution;
	}
}
