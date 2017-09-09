package com.kikia.itacon.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kikia.itacon.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);
	User findByEmail(String email);
}
