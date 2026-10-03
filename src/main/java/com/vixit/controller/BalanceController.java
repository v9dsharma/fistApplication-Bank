package com.vixit.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
//@ComponentScan("com.vixit.controller")
public class BalanceController
{
	@GetMapping("/myBalance")
	public String getBalanceDetails(){

		return "Balance details from DB";
	}
}
