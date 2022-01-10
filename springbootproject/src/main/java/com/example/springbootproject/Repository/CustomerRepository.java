package com.example.springbootproject.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.springbootproject.Model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long>{
	/*
	 *  need to match object property and capitalize first letter
	 *  what if first letter is already capitalized? doesn't work if first letter is capitalized
	 */
	List<Customer> findByLastName(String lastName);
	
	Customer findById(long id);

}
