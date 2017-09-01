package com.kikia.itacon.services;

import java.util.Optional;

import com.kikia.itacon.domain.User;
import com.kikia.itacon.dto.UserDTO;

public interface UserService {

	Iterable<User> listAllUsers();
	Optional<User> getUserById(Long id);
	Optional<User> getUserByEmail(String email);
	User saveUser(UserDTO user);
	void deleteUser(Long id);
	User findByUsername(String username);
}
