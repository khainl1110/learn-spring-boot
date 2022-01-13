package com.example.springbootproject.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.springbootproject.Model.Customer;
import com.example.springbootproject.Repository.CustomerRepository;

@RestController
@RequestMapping("/api/customers")
public class CustomerRestController {

	@Autowired 
	CustomerRepository repo;
	
	@GetMapping()
	public Iterable<Customer> getCustomers() {
		return repo.findAll();
	}
	
	@GetMapping("/{id}")
	public Customer getCustomer(@PathVariable("id") Long id) {
		Optional<Customer> data =  repo.findById(id);
		return data.get();
	}
	
	@PostMapping()
	public Customer createCustomer(@RequestBody Customer customer) {
		repo.save(customer);
		return customer;
	}
	
	@PutMapping()
	public Customer updateCustomer(@RequestBody Customer nCustomer) {
		Optional<Customer> data =  repo.findById(nCustomer.getId());
		Customer customer = data.get();
		customer.setFirstName(nCustomer.getFirstName());
		customer.setLastName(nCustomer.getLastName());
		return repo.save(customer);
	}
	
	@DeleteMapping("/{id}")
	public String deleteCustomer(@PathVariable("id") Long id) {
		repo.deleteById(id);
		return "delete";
	}
	
}
