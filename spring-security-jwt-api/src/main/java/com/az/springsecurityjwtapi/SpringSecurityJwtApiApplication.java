package com.az.springsecurityjwtapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(value="com.az")
@SpringBootApplication
public class SpringSecurityJwtApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityJwtApiApplication.class, args);
	}

}
