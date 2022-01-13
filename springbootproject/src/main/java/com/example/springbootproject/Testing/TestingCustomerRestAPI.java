package com.example.springbootproject.Testing;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.springbootproject.Controller.CustomerRestController;
import com.example.springbootproject.Model.Customer;
import com.example.springbootproject.Repository.CustomerRepository;

@WebMvcTest(CustomerRestController.class)
public class TestingCustomerRestAPI {

	@Autowired
	private MockMvc mockMvc;
	
	/*
	 * https://www.youtube.com/watch?v=vcRFkp8jHJ8
	 * https://www.baeldung.com/java-spring-mockito-mock-mockbean
	 * https://stackabuse.com/guide-to-unit-testing-spring-boot-rest-apis/
	 * https://docs.oracle.com/cd/E60058_01/PDF/8.0.8.x/8.0.8.0.0/PMF_HTML/JsonPath_Expressions.htm
	 */
	
	/*
	 *  since we are testing this, use MockBean
	 *  what happens if we use @Autowire here? error because it cannot find the 
	 *  bean specified in its class
	 */
	@MockBean
	CustomerRepository repo;
	Customer c1 = new Customer("Test1", "TestLastName");
	Customer c2 = new Customer("Whatever", "Hello");
	List<Customer> records = new ArrayList<>(Arrays.asList(c1,c2));
	
	@PostConstruct
	public void setUp() {
		
		// repo return records whenever findAll is invoke
		Mockito.when(repo.findAll()).thenReturn(records);
	}
	
	@Test
	public void getAllCustomers() throws Exception {
		
		this.mockMvc.perform(MockMvcRequestBuilders.get("/api/customers"))
			.andExpect(MockMvcResultMatchers.status().is(200))
			.andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(records.size()) ))
			.andExpect(MockMvcResultMatchers.jsonPath("$[1].lastName", Matchers.is(records.get(1).getLastName()) ))
			;
		
	}
	
	@Test
	public void getCustomer() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/api/customers"))
		.andExpect(MockMvcResultMatchers.status().is(200))
		.andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(2)))
		.andExpect(MockMvcResultMatchers.jsonPath("$[1].lastName", Matchers.is("Hello") ))
		;
	}
	

}
