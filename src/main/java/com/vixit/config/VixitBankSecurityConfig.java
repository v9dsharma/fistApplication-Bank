package com.vixit.config;

import static org.springframework.security.config.Customizer.withDefaults;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;


@Configuration
public class VixitBankSecurityConfig
{
	@Bean
	SecurityFilterChain defaultSecurityFilterChain(final HttpSecurity http) {
		http.csrf(csrfConfig-> csrfConfig.disable()); // we are disabling csrf for POST http methods as of now.
		http.authorizeHttpRequests((requests) ->
			requests.requestMatchers("/myAccount","/myBalance","/loans","/myCards").authenticated()
			.requestMatchers("/notices","/contact","/error","/register").permitAll());
		http.formLogin(withDefaults());
		http.httpBasic(withDefaults()); // if we disable this, APIs won't work using postman Auth type---> Basic Auth
		return http.build();
	}

	/* // as have created our custom UserDetailsService implementation, VixitBankUserDetailsService, we do not need this.
	@Bean
	UserDetailsService userDetails(final DataSource dataSource){

		final UserDetailsService userDetailsService= new JdbcUserDetailsManager(dataSource);

		return userDetailsService;
	}
*/
	@Bean
	public PasswordEncoder passwordEncoder(){

		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}


	// introduced in spring 6.3 onwards : this is for compromised passwords like, 12345, test@123, etc. as password.
	@Bean
	public CompromisedPasswordChecker compromisedPasswordChecker(){
		return new HaveIBeenPwnedRestApiPasswordChecker();
	}

}
