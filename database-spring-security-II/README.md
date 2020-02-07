# Simple-Spring-Security-api
It's a simple spring boot project in which I'm using spring security in which I'm using 

* I have added h2 embedded DataBase, JDBC api dependencies other than web and security
* No We need to tell Spring security to use JDBC Authentication without default schema and default users, so override `configure` method like that:

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {	
		auth.jdbcAuthentication()
		.dataSource(dataSource);
	}

* *For external DataSource you just need to configure your DataSource with your application and pass to AuthenticationManagerBuilder like above.*
	
	spring.datasource.url=
	spring.datasource.username=
	spring.datasource.password=

* Create schema.sql file for defining schema for users and their roles: [Please check Spring security Doc](https://docs.spring.io/spring-security/site/docs/current/reference/htmlsingle/#user-schema)
	
	CREATE TABLE USERS(
    USERNAME varchar_ignorecase(50) not null primary key,
    PASSWORD varchar_ignorecase(50) not null,
    ENABLED boolean not null
	);
	
	CREATE TABLE AUTHORITIES (
	    USERNAME varchar_ignorecase(50) not null,
	    AUTHORITY varchar_ignorecase(50) not null,
	    constraint fk_authorities_users foreign key(USERNAME) references USERS(USERNAME)
	);
	CREATE UNIQUE INDEX ix_auth_username on AUTHORITIES (USERNAME,AUTHORITY);

* In our case we are using H2 embedded Database so above will work fine.
* Now we need to insert users and their roles, for this we need to create data.sql file:

	INSERT INTO USERS (USERNAME, PASSWORD, ENABLED) 
		VALUES('Az','Az',true);
	
	INSERT INTO USERS (USERNAME, PASSWORD, ENABLED) 
		VALUES('Adi','Adi',true);
	
	INSERT INTO USERS (USERNAME, PASSWORD, ENABLED) 
		VALUES('Shah','Shah',true);
	
	INSERT INTO USERS (USERNAME, PASSWORD, ENABLED) 
		VALUES('Mughal','Mughal',true);
	
	INSERT INTO AUTHORITIES (USERNAME, AUTHORITY)
		VALUES('Az','ROLE_USER');
	
	INSERT INTO AUTHORITIES (USERNAME, AUTHORITY)
		VALUES('Adi','ROLE_ACCOUNT');
		
	INSERT INTO AUTHORITIES (USERNAME, AUTHORITY)
		VALUES('Shah','ROLE_ADMIN');
		
	INSERT INTO AUTHORITIES (USERNAME, AUTHORITY)
		VALUES('Mughal','ROLE_ADMIN');
 
* If schema is different then we need to tell spring security to get users and their authorities by this way:

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {	
		auth.jdbcAuthentication()
		.dataSource(dataSource)
		.usersByUsernameQuery("SELECT USERNAME, PASSWORD, ENABLED FROM USERS WHERE USERNAME = ?")
		.authoritiesByUsernameQuery("SELECT USERNAME, AUTHORITY FROM AUTHORITIES WHERE USERNAME = ?");		
	}
* In above way you can use your own tables like table name users to my_user or authorities table to user_authorities.
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
* In 6th example I will use Spring security with Spring Data JPA for Authentication (Users) and Authorization (Roles).
