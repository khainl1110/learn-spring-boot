package com.example.springbootproject.Controller;

import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.springbootproject.Model.Customer;
import com.example.springbootproject.Repository.CustomerRepository;

@Controller
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
	
  @GetMapping("/addCustomer")
  public String addCustomer(Model model) {
    model.addAttribute("customer", new Customer());
    return "customer-add";
  }

  @PostMapping("/addCustomer")
  public String addCustomerSubmit(@ModelAttribute Customer customer, Model model) {
    model.addAttribute("customer", customer);
    // add customer to repo
    repo.save(customer);
    return "customer-add-result";
  }
  
  @GetMapping("/view")
  public String viewCustomers(Model model) {
	model.addAttribute("customers", repo.findAll());
	return "customer-listAll";
  }
  
  @GetMapping("/login")
  public String login(Model model) {
	  model.addAttribute("customer", new Customer());
	  return "customer-login";
  }
  
  @PostMapping("/login")
  // model attribute doesn't need, just there to make it's clear
  public String loginResult(Model model, @ModelAttribute Customer customer) {
	  boolean bool = false;
	  
	  Optional<Customer> findResult= repo.findById(customer.getId());
	  if(findResult.isPresent()) 
		  bool = true;

	  model.addAttribute("bool", bool);
	  
	  return "customer-login-result";
  }
  // it thought this returns a thymeleaf template
//	@GetMapping("/testing")
//	public String getCustomer() {
//		return repo.findByLastName("Nguyen").toString();
//	}

}