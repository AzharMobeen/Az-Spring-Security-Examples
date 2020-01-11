package com.az.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.az.repositories")
@ComponentScan(basePackages="com.az")
@SpringBootApplication
@EntityScan(value = "com.az.entities")
public class SimpleSpringSecurityApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleSpringSecurityApiApplication.class, args);
	}

}
