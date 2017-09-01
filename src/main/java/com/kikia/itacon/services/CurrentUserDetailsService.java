package com.kikia.itacon.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kikia.itacon.domain.CurrentUser;
import com.kikia.itacon.domain.User;

@Service
public class CurrentUserDetailsService implements UserDetailsService {

	private UserService userService;

	@Autowired
	public void setUserRepository(UserService userService) {
		this.userService = userService;
	}

	@Override
	@Transactional(readOnly = true)
	public CurrentUser loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userService.findByUsername(username);
		CurrentUser currentUser = null;
		if (user != null) {
			currentUser = new CurrentUser(user);
		} else {
			currentUser = null;
			throw new UsernameNotFoundException(String.format("User with username=%s was not found", username));
		}

		return currentUser;
	}
}
