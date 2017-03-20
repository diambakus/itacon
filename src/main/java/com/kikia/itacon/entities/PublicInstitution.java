package com.kikia.itacon.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "public_service")
public class PublicInstitution extends BasicEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1281989781086981447L;

	private String name;
	private String code;
	@OneToMany(mappedBy = "publicInstitution", cascade = CascadeType.ALL)
	private Set<Service> services = new HashSet<Service>();

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

	public Set<Service> getServices() {
		return services;
	}

	public void setServices(Set<Service> services) {
		this.services = services;
	}
}
