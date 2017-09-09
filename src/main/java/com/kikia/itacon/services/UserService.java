package com.kikia.itacon.services;

import com.kikia.itacon.domain.User;
import com.kikia.itacon.dto.UserDTO;

public interface UserService {

	Iterable<User> listAllUsers();
	User getUserById(Long id);
	User getUserByEmail(String email);
	User saveUser(UserDTO user);
	void deleteUser(Long id);
	User findByUsername(String username);
}
