package com.kikia.itacon.entities;

import javax.persistence.Entity;

@Entity
public class User extends BasicEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3696032361561803463L;

	private String name;
	private String userName;
	private boolean enabled;
    private String password;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
