package com.kikia.itacon.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "institution")
public class Institution implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1281989781086981447L;
    private Long id;
	private String name;
	private String code;
	private Set<Service> services = new HashSet<Service>();
	private Set<User> users = new HashSet<User>();

	
	public void setId(Long id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
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

	@OneToMany(mappedBy = "institution", cascade = CascadeType.ALL, orphanRemoval = true)
	public Set<Service> getServices() {
		return services;
	}

	public void setServices(Set<Service> services) {
		this.services = services;
	}
	
	@OneToMany(mappedBy = "institution", cascade = CascadeType.ALL, orphanRemoval = true)
	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}
}
