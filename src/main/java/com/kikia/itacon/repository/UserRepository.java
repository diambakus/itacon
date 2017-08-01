package com.kikia.itacon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.kikia.itacon.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);
}
