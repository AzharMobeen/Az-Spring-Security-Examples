package com.az.ssJWT.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.az.models.AuthenticationRequest;
import com.az.models.AuthenticationResponse;
import com.az.services.JwtUtil;
import com.az.services.MyUserDetailsService;
import com.az.springsecurityjwtapi.MyAuthenticationProvider;

@RestController
public class HelloController {
/*
	@Autowired
	private AuthenticationManager authenticationManager;*/
	
	@Autowired
	private MyAuthenticationProvider myAuthenticationProvider;
	
	@Autowired
	private MyUserDetailsService userDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@GetMapping("/hello")
	public String hello() {
		return "Hello World!";
	}
	
	
	@PostMapping("/authentication")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
		
		try {
			myAuthenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUserName(), authenticationRequest.getPassword()));
			
		}catch (Exception e) {
			throw new Exception("Incorrect UserName or Password");
		}
		// it it's successful then bellow lines will be executed else it'll throw exception
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUserName());
		final String jwt = jwtUtil.generateToken(userDetails);
		
		return ResponseEntity.ok(new AuthenticationResponse(jwt)); 
	}
}
