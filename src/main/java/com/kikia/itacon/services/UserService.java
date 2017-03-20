package com.kikia.itacon.services;

import com.kikia.itacon.entities.User;

public interface UserService {

	Iterable<User> listAllUsers();
	User getUserById(Long id);
	User saveUser(User user);
	void deleteUser(Long id);
}
