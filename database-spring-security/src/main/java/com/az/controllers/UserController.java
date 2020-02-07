package com.az.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {	
	
	@GetMapping({"/"})
	public String home() {
		return "Hom Page for all users!";
	}
	
	@GetMapping({"/user"})
	public String user() {
		return "User Role Page!";
	}
	
	@GetMapping({"/admin"})
	public String admin() {
		return "Admin Role Page!";
	}
	
	@GetMapping({"/accounts"})
	public String accounts() {
		return "Accounts Role Page!";
	}			
		
}
