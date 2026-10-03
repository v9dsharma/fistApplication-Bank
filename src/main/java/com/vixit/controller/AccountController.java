package com.vixit.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
//@ComponentScan("com.vixit.controller")
public class AccountController
{
	@GetMapping("/myAccount")
	public String getAccountDetails(){
		return "Get Account details from DB";
	}
}
