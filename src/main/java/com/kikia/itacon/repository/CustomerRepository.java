package com.kikia.itacon.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kikia.itacon.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
