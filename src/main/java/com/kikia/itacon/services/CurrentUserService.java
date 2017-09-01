package com.kikia.itacon.services;

import com.kikia.itacon.domain.CurrentUser;

public interface CurrentUserService {
	boolean canAccessUser(CurrentUser currentUser, Long userId);
}
