package com.example.springbootproject.Testing;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.example.springbootproject.Controller.CustomerRestController;
import com.example.springbootproject.Model.Customer;
import com.example.springbootproject.Repository.CustomerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(CustomerRestController.class)
public class TestingCustomerRestAPI {
	/*
	 * This only include success testing case, need to update with failure case as well
	 */
	@Autowired
	ObjectMapper mapper;
	
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
	Customer c1 = new Customer(1l,"Test1", "TestLastName");
	Customer c2 = new Customer(2l,"Whatever", "Hello");
	List<Customer> records = new ArrayList<>(Arrays.asList(c1,c2));
	
	@Test
	public void getAllCustomers_success() throws Exception {
		// repo return records whenever findAll is invoke
		Mockito.when(repo.findAll()).thenReturn(records);
		
		this.mockMvc.perform(MockMvcRequestBuilders.get("/api/customers"))
			.andExpect(MockMvcResultMatchers.status().is(200))
			.andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(records.size()) ))
			.andExpect(MockMvcResultMatchers.jsonPath("$[1].lastName", Matchers.is(records.get(1).getLastName()) ))
			;
		
	}
	
	@Test
	public void getCustomerById() throws Exception {
		Mockito.when(repo.findById(c1.getId()) ).thenReturn(java.util.Optional.of(c1))	;	
		
		this.mockMvc.perform(MockMvcRequestBuilders.get("/api/customers/1"))
		.andExpect(MockMvcResultMatchers.status().is(200))
		.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.notNullValue() ))
		.andExpect(MockMvcResultMatchers.jsonPath("$.lastName", Matchers.is("TestLastName") ))
		;
	}
	
	@Test
	public void createRecord_success() throws Exception {
		Mockito.when(repo.save(c1)).thenReturn(c1);
		
		MockHttpServletRequestBuilder mockReq = MockMvcRequestBuilders.post("/api/customers")
				.content(this.mapper.writeValueAsBytes(c1))
				// need content type json otherwise it will said unsupported data type 415 error
				.contentType(MediaType.APPLICATION_JSON)
				
				;
		
		this.mockMvc.perform(mockReq)
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.notNullValue() ))
			.andExpect(MockMvcResultMatchers.jsonPath("$.lastName", Matchers.is(c1.getLastName()) ));
	}
	
	@Test
	public void updatePatientRecord_success() throws Exception {
		Customer updateRecord = new Customer(1l, "Test3", "Update");
		
		Mockito.when(repo.findById(c1.getId())).thenReturn(Optional.of(c1));
		Mockito.when(repo.save(updateRecord)).thenReturn(updateRecord);
		
		MockHttpServletRequestBuilder mockReq = MockMvcRequestBuilders.post("/api/customers")
				.contentType(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsBytes(updateRecord));
		
		mockMvc.perform(mockReq)
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(jsonPath("$", Matchers.notNullValue() ))
			.andExpect(jsonPath("$.lastName", Matchers.is("Update") ));
			;
	}
	
	@Test
	public void deletePatientById_success() throws Exception {
		Mockito.when(repo.findById(c2.getId() )).thenReturn(Optional.of(c2));
		
		this.mockMvc.perform(MockMvcRequestBuilders.delete("/api/customers/"+c2.getId()))
				.andExpect(MockMvcResultMatchers.status().isOk())
				
		;
		
	}
}
