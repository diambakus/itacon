package com.kikia.itacon.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.kikia.itacon.domain.User;
import com.kikia.itacon.dto.UserDTO;
import com.kikia.itacon.repository.UserRepository;

/**
 * It also implements UserDetailsServiceImpl for login/authentication purpose
 * 
 * @author diambakus
 *
 */
@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;

	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public Iterable<User> listAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User getUserById(Long id) {
		return userRepository.findOne(id);
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public User getUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public User saveUser(UserDTO userDTO) {
		User user;
		if (userDTO.getId() != null) {
			user = getUserById(userDTO.getId());
		}else {
			user = new User();
		}
		if (!userDTO.getEmail().equals(""))
			user.setEmail(userDTO.getEmail());
		if (!userDTO.getFirstName().equals(""))
			user.setFirstName(userDTO.getFirstName());
		if (!userDTO.getLastName().equals(""))
			user.setLastName(userDTO.getLastName());
		if (!userDTO.getUsername().equals(""))
			user.setUsername(userDTO.getUsername());
		if (!userDTO.getPassword().equals(""))
			user.setPassword((new BCryptPasswordEncoder().encode(userDTO.getPassword())).toCharArray());
		if (userDTO.getRole() != user.getRole())
			user.setRole(userDTO.getRole());
		if (userDTO.isEnable() != user.isEnable())
			user.setEnable(userDTO.isEnable());
		
		return userRepository.save(user);
	}

	@Override
	public void deleteUser(Long id) {
		userRepository.delete(id);
	}
}
