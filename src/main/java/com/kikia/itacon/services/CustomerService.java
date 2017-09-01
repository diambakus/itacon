package com.kikia.itacon.services;

import com.kikia.itacon.domain.Customer;

public interface CustomerService {

	Iterable<Customer> listAllCustomers();

	Customer getCustomerById(Long Id);
	
	Customer getCustomerByNIF(Long NIF);

	Customer saveCustomer(Customer customer);

	void deleteCustomer(Long Id);
}
