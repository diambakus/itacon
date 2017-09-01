package com.kikia.itacon.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * It provides current loggedin user and auto login user after registering an
 * account
 * 
 * @author diambakus
 *
 */
//@Service
public class SecurityServiceImpl implements SecurityService {

	private AuthenticationManager authenticationManager;
	private UserDetailsService userDetailsService;

	private static final Logger logger = LoggerFactory.getLogger(SecurityServiceImpl.class);
	
	@Autowired
	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}
	
	@Autowired
	public void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	@Override
	public String findLoggedInUsername() {

		Object userDetails = SecurityContextHolder.getContext().getAuthentication();
		logger.info("42 MINHA LOG " + userDetails.toString());

		if (userDetails instanceof UserDetails)
			return ((UserDetails) userDetails).getUsername();
		return null;
	}

	@Override
	public void autologin(String username, String password) {

		UserDetails userDetails = userDetailsService.loadUserByUsername(username);
		logger.info(" 53 MINHA LOG " + userDetails.toString());

		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
				userDetails, password, userDetails.getAuthorities());

		authenticationManager.authenticate(usernamePasswordAuthenticationToken);

		if (usernamePasswordAuthenticationToken.isAuthenticated()) {
			SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			logger.debug(String.format("Auto login %s successfully!", username));
		}

	}

}
