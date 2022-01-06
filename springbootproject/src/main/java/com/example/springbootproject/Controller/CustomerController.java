package com.example.springbootproject.Controller;

import java.util.Iterator;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springbootproject.Model.Customer;
import com.example.springbootproject.Repository.CustomerRepository;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	CustomerRepository repo;
	
	@PostConstruct
	void addCustomers() {
		repo.save(new Customer("Khai", "Nguyen"));
		repo.save(new Customer("Whatever", "Haha"));
		repo.save(new Customer("Thanh", "Nguyen"));
		
	}
	
	@GetMapping("/hi")
	public String hello() {
		return "Hello";
	}
	
	@GetMapping("/testing")
	public String getCustomer() {
		return repo.findByLastName("Nguyen").toString();
	}
	
}
