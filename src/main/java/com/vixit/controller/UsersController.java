package com.vixit.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vixit.model.Customer;
import com.vixit.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
public class UsersController
{
	private final CustomerRepository customerRepository;
	private final PasswordEncoder passwordEncoder;

	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody final Customer customer){

		try{

			if(customerRepository.findByEmail(customer.getEmail()).isPresent()){
				return ResponseEntity.status(HttpStatus.CONFLICT).body("Given user is already present");
			}

		final String hashPswd = passwordEncoder.encode(customer.getPassword());
		customer.setPassword(hashPswd);
		final Customer cust = customerRepository.save(customer);
		if(cust.getId()>0){
			return ResponseEntity.status((HttpStatus.CREATED))
					.body("Customer registration is successful");
		}else {
			return ResponseEntity.status((HttpStatus.BAD_REQUEST))
					.body("Customer registration failed!");
		}

		}catch (final Exception e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("an exception occurred: "+e.getMessage());

		}

	}
}
