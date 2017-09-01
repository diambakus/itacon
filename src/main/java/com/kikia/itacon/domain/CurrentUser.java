package com.kikia.itacon.domain;

import org.springframework.security.core.authority.AuthorityUtils;

public class CurrentUser extends org.springframework.security.core.userdetails.User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7695543175335994633L;
	private User user;

	public CurrentUser(User user) {
		/**
		 * Construct the <code>User</code> with the details required by
		 * {@link org.springframework.security.authentication.dao.DaoAuthenticationProvider}.
		 *
		 * @param username
		 *            the username presented to the
		 *            <code>DaoAuthenticationProvider</code>
		 * @param password
		 *            the password that should be presented to the
		 *            <code>DaoAuthenticationProvider</code>
		 * @param enabled
		 *            the value should be updated(to <code>true</code>) by
		 *            admin. Otherwise, remains <code>false</code>
		 * @param accountNonExpired
		 *            set to <code>true</code> if the account has not expired
		 * @param credentialsNonExpired
		 *            set to <code>true</code> if the credentials have not
		 *            expired
		 * @param accountNonLocked
		 *            set to <code>true</code> if the account is not locked
		 * @param authorities
		 *            the authorities that should be granted to the caller if
		 *            they presented the correct username and password and the
		 *            user is enabled. Not null.
		 */
		super(user.getUsername(), new String(user.getPassword()), user.isEnable(), true, true, true,
				AuthorityUtils.createAuthorityList(user.getRole().toString()));
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public Long getId() {
		return user.getId();
	}

	public Role getRole() {
		return user.getRole();
	}

	@Override
	public String toString() {
		return "CurrentUser{" + "user=" + user + "} " + super.toString();
	}

}
