# Simple-Spring-Security-api
It's a simple spring boot project in which I'm using spring security

* I have added h2 embedded DataBase, JDBC api dependencies other than web and security
* No We need to tell Spring security to use JDBC Authentication with default schema and default users, so override `configure` method like that:

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
	
* Spring security create table with default schema with provided default users and roles
* Need to setup Authorization as well for user Roles:

	@Override
	protected void configure(HttpSecurity http) throws Exception {
	
		http.authorizeRequests()		
		.antMatchers("/user").hasRole("USER")
		.antMatchers("/admin").hasRole("ADMIN")
		.antMatchers("/accounts").hasRole("ACCOUNT")
		.antMatchers("/").permitAll()
		.and().formLogin();
	}

* Run as spring boot application and hit bellow url:
* [localhost:8080/hello](http://localhost:8080/hello)
* username/password [Az/Az, USER], [Adi/Adi, ACCOUNT], [Shah/Shah, ADMIN], [Mughal/Mughal, ADMIN]

## Next Example:
* In 5th example I will use Spring security with defined schema (schema.sql) and defined users data (data.sql) for Authentication (Users) and Authorization (Roles).