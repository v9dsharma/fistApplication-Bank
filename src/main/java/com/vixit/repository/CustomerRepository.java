package com.vixit.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vixit.model.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer,Long>
{
	Optional<Customer> findByEmail(String email); // if we follow the proper JPA naming
	// standard then we do not need to write implementation logic.

}
