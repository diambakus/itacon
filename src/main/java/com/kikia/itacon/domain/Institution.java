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
	private Set<OfferedService> offeredServices = new HashSet<>();
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
	public Set<OfferedService> getOfferedServices() {
		return offeredServices;
	}

	public void setOfferedServices(Set<OfferedService> services) {
		this.offeredServices = services;
	}

	@OneToMany(mappedBy = "institution", cascade = CascadeType.ALL, orphanRemoval = true)
	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		return prime * (((name == null) && (id == null)) ? 0 : (name.hashCode() + id.hashCode()));
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Institution))
			return false;
		Institution other = (Institution) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
