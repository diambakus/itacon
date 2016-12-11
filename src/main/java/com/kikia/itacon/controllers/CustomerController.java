package com.kikia.itacon.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kikia.itacon.entities.Customer;
import com.kikia.itacon.services.CustomerService;

@Controller
public class CustomerController {

	private CustomerService customerService;

	@Autowired
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	@GetMapping(value = "/customers")
	public String list(Model model) {
		model.addAttribute("customers", customerService.listAllCustomers());
		return "customers";
	}

	@RequestMapping("customer/{id}")
	public String showCustomer(@PathVariable Long id, Model model) {
		model.addAttribute("customer", customerService.getCustomerById(id));
		return "customer";
	}

	@RequestMapping("customer/edit/{id}")
	public String edit(@PathVariable Long id, Model model) {
		model.addAttribute("customer", customerService.getCustomerById(id));
		return "editcustomer";
	}

	@GetMapping(value = "/new")
	public String createNewCustomer(Model model) {
		model.addAttribute("customer", new Customer());
		return "new";
	}
	
	@PostMapping(value = "/new")
    public String saveProduct(Customer customer){
		customerService.saveCustomer(customer);
        return "result";
    }

    @RequestMapping("customer/delete/{id}")
    public String delete(@PathVariable Long id){
    	customerService.deleteCustomer(id);
        return "redirect:/customers";
    }
}
