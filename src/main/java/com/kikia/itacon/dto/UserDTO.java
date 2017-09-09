package com.kikia.itacon.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.kikia.itacon.domain.Role;

public class UserDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4174502180114192699L;
	private Long id = null;
	@NotEmpty
	private String firstName = "";
	@NotEmpty
	private String lastName = "";
	@NotEmpty
	private String username="";
	@NotEmpty
	private String password="";
	@NotEmpty
	private String passwordConfirm="";
	@NotEmpty
	private String email="";
	@NotNull
	private Role role=Role.USER;
	@NotNull
	private boolean enable = false;
    

	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPasswordConfirm() {
		return passwordConfirm;
	}
	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public boolean isEnable() {
		return enable;
	}
	public void setEnable(boolean enable) {
		this.enable = enable;
	}
}
