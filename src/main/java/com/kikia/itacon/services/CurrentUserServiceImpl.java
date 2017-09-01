package com.kikia.itacon.services;

import org.springframework.stereotype.Service;

import com.kikia.itacon.domain.CurrentUser;
import com.kikia.itacon.domain.Role;

@Service
public class CurrentUserServiceImpl implements CurrentUserService {

	@Override
	public boolean canAccessUser(CurrentUser currentUser, Long userId) {

		boolean isValidRole = false;
		if (currentUser != null) {

			isValidRole = ((currentUser.getRole() == Role.ADMIN) || (currentUser.getRole() == Role.AUDIT)
					|| (currentUser.getRole() == Role.SUPERADMIN) || (currentUser.getId().equals(userId)));
		}
		return isValidRole;
	}
}
