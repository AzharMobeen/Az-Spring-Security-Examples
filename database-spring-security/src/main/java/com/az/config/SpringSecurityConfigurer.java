package com.az.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SpringSecurityConfigurer extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private DataSource dataSource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {	
		auth.jdbcAuthentication()
		.dataSource(dataSource)
		.withDefaultSchema()
		.withUser(
				User.withUsername("Az").password("Az").roles("USER")
				)
		.withUser(
				User.withUsername("Adi").password("Adi").roles("ACCOUNT")
				)
		.withUser(
				User.withUsername("Shah").password("Shah").roles("ADMIN")
				)		
		.withUser(
				User.withUsername("Mughal").password("Mughal").roles("ADMIN")
				);
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
