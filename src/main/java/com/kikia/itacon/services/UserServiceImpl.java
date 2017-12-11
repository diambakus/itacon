package com.kikia.itacon.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.kikia.itacon.domain.Institution;
import com.kikia.itacon.domain.Role;
import com.kikia.itacon.domain.User;
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
	public User saveUser(User user) {
		User savingUser = null; /* It holds retrieved user or new user */
		if (user != null) {
			String email = user.getEmail();
			String firstName = user.getFirstName();
			Institution institution = user.getInstitution();
			String lastName = user.getLastName();
			Role role = user.getRole();
			String username = user.getUsername();
			boolean isEnable = user.isEnable();

			if ((user.getId() != null))
				savingUser = getUserById(user.getId());
			else {
				savingUser = new User();
				savingUser.setRole(Role.USER);
				if ((!user.getPassword().isEmpty()) && (!user.getPasswordConfirm().isEmpty())
						&& (user.getPassword().length() >= 8) && (user.getPassword().equals(user.getPasswordConfirm())))
					savingUser.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
			}

			if ((!email.isEmpty()) && (email != savingUser.getEmail()))
				savingUser.setEmail(email);
			if ((!firstName.isEmpty()) && (firstName != savingUser.getFirstName()))
				savingUser.setFirstName(firstName);
			if ((institution != null) && (institution.getId() != savingUser.getInstitution().getId()))
				savingUser.setInstitution(institution);
			if ((!lastName.isEmpty()) && (lastName != savingUser.getLastName()))
				savingUser.setLastName(lastName);
			if ((role != null))
				savingUser.setRole(role);
			if (!username.isEmpty() && !(username.equals(savingUser.getUsername())))
				savingUser.setUsername(username);
			if (isEnable != savingUser.isEnable())
				savingUser.setEnable(isEnable);
			return userRepository.save(savingUser);
		}
		return null;
	}

	@Override
	public void updateStatus(Long id, boolean flag) {

		User user = userRepository.findOne(id);
		if (user != null) {
			user.setEnable(flag);
			userRepository.save(user);
		}
	}

	@Override
	public void updateRole(Long id, Role role) {
		// TODO Auto-generated method stub
	}

	@Override
	public void deleteUser(Long id) {
		userRepository.delete(id);
	}
}
