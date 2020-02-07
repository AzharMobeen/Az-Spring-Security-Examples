package com.az.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService{

private List<User> userList = null;
	
	public MyUserDetailsService() {
		
		User user1= new User("Az", "Az", new ArrayList<>());
		User user2= new User("Adi", "Adi", new ArrayList<>());
		User user3= new User("Shah", "Shah", new ArrayList<>());
		User user4= new User("Mughal", "Mughal", new ArrayList<>());
		userList = Arrays.asList(user1,user2,user3,user4);
	}
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userList.stream().filter(userTemp->userTemp.getUsername().equals(username)).findFirst().orElse(null); 
		return user;
	}


}
