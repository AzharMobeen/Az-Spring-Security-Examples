# Simple-Spring-Security-api
It's a simple spring boot project in which I'm using spring security

* Create simple spring boot project from https://start.spring.io/ and add only Web and Security module
* Then open this project into any IDE as maven project
* Create SpringSecurityConfigurer class and extends WebSecurityConfigurerAdaptor
* Now override configure method with (custom class) myUserDetailsService class reference.
* Create MyUserDetailsService class and impletment UserDetailsService class. 
* Now overirde loadUserByUsername method in which you can configure inmemory user object.
* Run as spring boot application and hit bellow url:
* [localhost:8080/hello](http://localhost:8080/hello)
* username/password [Az/Az]
