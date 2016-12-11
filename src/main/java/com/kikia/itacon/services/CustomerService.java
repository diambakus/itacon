package com.kikia.itacon.services;

import com.kikia.itacon.entities.Customer;

public interface CustomerService {

	Iterable<Customer> listAllCustomers();

	Customer getCustomerById(Long id);

	Customer saveCustomer(Customer customer);

	void deleteCustomer(Long id);
}
