package com.kikia.itacon.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3696032361561803463L;
	private Long id;
	private String firstName;
	private String lastName;
	private String username;
	private char[] password;
	private String email;
	private Role role;
	private PublicInstitution publicInstitution;
	private boolean enable;

	public void setId(Long id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String userName) {
		this.username = userName;
	}

	public char[] getPassword() {
		return password;
	}

	public void setPassword(char[] password) {
		this.password = password;
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

	@Column(columnDefinition = "varchar", nullable = false, length = 10)
	@Enumerated(EnumType.STRING)
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "public_institution_id")
	public PublicInstitution getPublicInstitution() {
		return publicInstitution;
	}

	public void setPublicInstitution(PublicInstitution publicInstitution) {
		this.publicInstitution = publicInstitution;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public String toString() {
		return "[firstName: " + firstName + " lastName: " + lastName + " username: " + username + " email: " + email
				+ " Role: " + role.name() + " status:" + isEnable() + "]";
	}
}
