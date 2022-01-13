package com.example.springbootproject.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
	@RequestMapping("/")
	public @ResponseBody String greeting() {
	
		return "Hello to the app \n Head to /customer/login to login "
				+ "\n /customer/view to view existing customers and their id "
				+ "\n /customer/addCustomer to addCustomer";
	}
	
	
}
