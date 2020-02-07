# Simple-Spring-Security-api (2nd Example)
It's a simple spring boot project in which I'm using spring security

* Create simple spring boot project from https://start.spring.io/ and add only Web and Security module
* Then open this project into any IDE as maven project
* First of all We need to configure Authentication Manager builder that deals spring security authentication manager.
* For this we need to Create SpringSecurityConfigurer class and extends WebSecurityConfigurerAdaptor.
* Now override configure method for Authentication.
	
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

* Run as spring boot application and hit bellow url:
* [localhost:8080/hello](http://localhost:8080/hello)
* username/password [Az/Az], [Adi/Adi], [Shah/Shah], [Mughal/Mughal]

### Next Example:
* [In 3rd example I will use inMemory List of users with different roles for spring security Authorization.](/InMemory-roleBase-spring-security)
