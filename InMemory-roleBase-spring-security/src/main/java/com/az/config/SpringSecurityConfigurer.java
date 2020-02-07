package com.az.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SpringSecurityConfigurer extends WebSecurityConfigurerAdapter{
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {	
		auth.inMemoryAuthentication()
		.withUser("Az").password("Az").roles("USER")
		.and()
		.withUser("Adi").password("Adi").roles("ACCOUNT")
		.and()
		.withUser("Shah").password("Shah").roles("ADMIN")
		.and()
		.withUser("Mughal").password("Mughal").roles("ADMIN");
	}	

	@Override
	protected void configure(HttpSecurity http) throws Exception {
	
		http.authorizeRequests()		
		.antMatchers("/user").hasRole("USER")
		.antMatchers("/admin").hasRole("ADMIN")
		.antMatchers("/accounts").hasRole("ACCOUNT")
		.antMatchers("/").permitAll()
		.and().formLogin();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {return NoOpPasswordEncoder.getInstance();}		
}
