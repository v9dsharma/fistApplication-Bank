package com.vixit.config;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vixit.model.Customer;
import com.vixit.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class VixitBankUserDetailsService implements UserDetailsService
{
	private final CustomerRepository customerRepository;
	/**
	 * @param username
	 * 		the username identifying the user whose data is required.
	 *
	 * @return
	 * @throws UsernameNotFoundException
	 */
	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException
	{
		final Customer customer = customerRepository.findByEmail(username).orElseThrow(()->
				new UsernameNotFoundException("Customer is not found for username" + username));

		final List<GrantedAuthority> grantedAuthorities = List.of(new SimpleGrantedAuthority(customer.getRole()));

		return new User(customer.getEmail(),customer.getPassword(),grantedAuthorities);
	}
}
