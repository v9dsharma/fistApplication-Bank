package com.vixit.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
//@ComponentScan("com.vixit.controller")
public class ContactController
{
	@GetMapping("/contact")
	public String getContactDetails(){

		return "Contact details from DB";
	}
}
