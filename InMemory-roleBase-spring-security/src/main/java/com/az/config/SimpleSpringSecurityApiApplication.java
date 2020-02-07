package com.az.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages="com.az")
@SpringBootApplication
public class SimpleSpringSecurityApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleSpringSecurityApiApplication.class, args);
	}

}
