# Simple-Spring-Security-api
It's a simple spring boot project in which I'm using spring security

* As per previous example in this example I'll override http configure for authorization
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	
		http.authorizeRequests()
		.antMatchers("/user").hasRole("USER")
		.antMatchers("/admin").hasRole("ADMIN")
		.antMatchers("/accounts").hasRole("ACCOUNT")
		.and().formLogin();
	}
 
* Run as spring boot application and hit bellow url:
* [localhost:8080/hello](http://localhost:8080/hello)
* [logout](http://localhost:8080/logout)
* username/password [Az/Az], [Adi/Adi], [Shah/Shah], [Mughal/Mughal]

## Next Example:
* In 4th example I will use Spring security with Database List of users with different roles.
