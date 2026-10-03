package com.vixit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;


@SpringBootApplication
@EnableWebSecurity // this is optional in SpringBoot application but required in other applications which are using spring security.
//@EnableJpaRepositories("com.vixit.repository")
//@EntityScan("com.vixit.model")  // these 2 annotations are required when we have main application class and Entity & Repo classes in different pkg.

public class VixitBankApplication {

	public static void main(final String[] args) {

		SpringApplication.run(VixitBankApplication.class, args);
	}

}
