package com.vixit.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
//@ComponentScan("com.vixit.controller")
public class CardsController
{
	@GetMapping("/myCards")
	public String getMyCards(){

		return "Fetch card details from DB";
	}
}
