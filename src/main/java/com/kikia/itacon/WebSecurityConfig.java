package com.kikia.itacon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private UserDetailsService userDetailsService;

	@Autowired
	public void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}
	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
		        .authorizeRequests()
				        .antMatchers("/resources/**" , "/", "/registration").permitAll()
				        .antMatchers("/dashboard/admin/**").hasAnyAuthority("ADMIN", "SUPERADMIN", "AUDIT")
						.anyRequest().fullyAuthenticated()
						.and()
				.formLogin()
				        .loginPage("/login")
				        .permitAll()
				        .failureUrl("/login?error")
				        .defaultSuccessUrl("/dashboard")
				        .usernameParameter("username")
				        .and()
				.logout()
						.logoutUrl("/logout")
						.logoutSuccessUrl("/").permitAll()
						.and().exceptionHandling()
						.accessDeniedPage("/access-denied");
	}

	@Override
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}
}
