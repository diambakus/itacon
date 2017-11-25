package com.kikia.itacon.services;

import com.kikia.itacon.domain.User;

public interface UserService {

	Iterable<User> listAllUsers();
	User getUserById(Long id);
	User getUserByEmail(String email);
	User saveUser(User user);
	void deleteUser(Long id);
	User findByUsername(String username);
}
