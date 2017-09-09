package com.kikia.itacon.controllers;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kikia.itacon.domain.Customer;
import com.kikia.itacon.domain.User;
import com.kikia.itacon.services.CustomerService;
import com.kikia.itacon.services.UserService;

@Controller
public class CustomerController {

	private final Logger logger = LoggerFactory.getLogger(CustomerController.class);

	private CustomerService customerService;
	private UserService userService;

	@Autowired
	public  CustomerController(CustomerService customerService, UserService userService) {
		this.customerService = customerService;
		this.userService = userService;
	}

	@GetMapping(value = "/customers")
	public String fragmentCustomers(Model model) {
		logger.debug("list()");
		model.addAttribute("customers", customerService.listAllCustomers());
		return "customers :: customers";
	}

	@GetMapping(value = "/customerslist")
	public String listOfCustomers(Model model, Principal principal) {
		logger.debug("list()");
		User user = userService.findByUsername(principal.getName());
		model.addAttribute("user", user);
		model.addAttribute("customers", customerService.listAllCustomers());
		return "serviceuser";
	}

	@RequestMapping("/searchcustomer")
	public String searchCustomer(Model model) {
		model.addAttribute("customers", customerService.listAllCustomers());
		return "searchcustomer";
	}

	@GetMapping("/searchcustomer/{queriedcustomer}")
	public String findCustomer(@PathVariable("queriedcustomer") String identity, Model model) {
		
		boolean found = false;
		Customer customer, targetCustomer = null;
        Iterable<Customer> allCustomers = customerService.listAllCustomers();
		
        for (Iterator<Customer> iterator = allCustomers.iterator(); iterator.hasNext() && (!found);) {
        	customer = iterator.next();
			if (customer.getBI().equalsIgnoreCase(identity)) {
				targetCustomer = customer;
				found = true;
			}
		}
        
        model.addAttribute("customers", targetCustomer);
		return "customers :: customers";
	}

	@RequestMapping("customer/{Id}")
	public String showCustomer(@PathVariable("Id") Long id, Model model) {
		logger.debug("showCustomer()");
		model.addAttribute("customer", customerService.getCustomerById(id));
		return "customer";
	}

	@RequestMapping("customer/edit/{Id}")
	public String edit(@PathVariable("Id") Long id, Model model) {
		model.addAttribute("customer", customerService.getCustomerById(id));
		return "editcustomer";
	}

	@GetMapping(value = "customer/register")
	public String createNewCustomer(Model model) {
		logger.debug("createNewCustomer()");
		model.addAttribute("customer", new Customer());
		model.addAttribute("customers", customerService.listAllCustomers());
		return "customerregister";
	}

	@RequestMapping("customer/updatebalace/{Id}")
	public String showUpdateBalanceForm(@PathVariable("Id") Long Id, Model model) {

		logger.debug("showUpdateBalanceForm()");
		Customer customer = customerService.getCustomerById(Id);
		model.addAttribute("customer", customer);

		return "customerupdatebalance";
	}

	@PostMapping(value = "customer/{Id}/updatebalance")
	public String updateBalance(@RequestParam("recharge_amount") BigDecimal balance, @PathVariable("Id") Long Id,
			Model model) {

		Customer customer = customerService.getCustomerById(Id);

		logger.debug("updateBalance()");
		customerService.saveCustomer(customer);
		model.addAttribute("customers", customerService.listAllCustomers());

		return "redirect:/dashboard";
	}

	/*
	 * TODO after insert an user it should show table of user with option to
	 * update credit
	 */
	@PostMapping(value = "customer/register")
	public String saveProduct(@ModelAttribute("customer") Customer customer, Model model) {

		logger.debug("saveProduct()");
		customerService.saveCustomer(customer);
		model.addAttribute("customer", customer);
		return "redirect:/customerslist";
	}

	@RequestMapping("customer/delete/{Id}")
	public String delete(@PathVariable("Id") Long id) {
		customerService.deleteCustomer(id);
		return "redirect:/customers";
	}
}
