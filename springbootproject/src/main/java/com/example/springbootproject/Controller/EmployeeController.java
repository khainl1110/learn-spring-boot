package com.example.springbootproject.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.springbootproject.Model.Employee;

// controller instead of rest controller 
@Controller
@RequestMapping("/employees")
public class EmployeeController {

	// load employee data
	
	private List<Employee> theEmployees;
	
	@PostConstruct
	private void loadData() {
		Employee emp1 = new Employee(1, "Nguyen", "Khai", "khainl1110@gmail.com");
		Employee emp2 = new Employee(2, "Ashile", "Andrew", "whatever@gmail.com");
		Employee emp3 = new Employee(3, "Haha", "Service", "khainl1110@gmail.com");
		
		theEmployees = new ArrayList<>();
		theEmployees.add(emp1);
		theEmployees.add(emp2);
		theEmployees.add(emp3);
	}
	
	@GetMapping("/list")
	public String listEmployees(Model theModel) {
		// add to the spring model
		theModel.addAttribute("employees", theEmployees);
		
		return "list-employees";
	}
	
	@GetMapping("/test")
	public String test(Model theModel) {
		theModel.addAttribute("test", "haha");
		
		return "test";
	}
}
