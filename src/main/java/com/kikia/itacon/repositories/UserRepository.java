package com.kikia.itacon.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.kikia.itacon.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
