# Simple-Spring-Security-api
It's a simple spring boot project in which I'm using spring security with JPA

* I have added MySQL DataBase server,Spring data JPA dependencies other than web and security
* Now We need to tell Spring security to use JPA Data source for Authentication so override method like that:

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {	
		auth.userDetailsService(userDetailService);
	}
* For JPA base spring security we create UserDetailService instance and for this we need to implement UserDetailService interface.
	
	@Service
	public class MyUserDetailsService implements UserDetailsService{

		@Autowired
		private UserRepository userRepository;
				
		@Override
		public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
			Optional<User> user = userRepository.findByUserName(username);
			user.orElseThrow(()->new UsernameNotFoundException("User Not found"));
			return user.map(MyUserDetails::new).get();
		}
	}
	

* *We have to configure data source.*
	
	spring.datasource.url=
	spring.datasource.username=
	spring.datasource.password=

* Create schema.sql file for defining schema for users and their roles: [Please check Spring security Doc](https://docs.spring.io/spring-security/site/docs/current/reference/htmlsingle/#user-schema)
	
	drop TABLE  IF EXISTS `AUTHORITIES`,`USERS`;
	CREATE TABLE USERS(
		Id int	not null AUTO_INCREMENT primary key ,
	    user_name varchar(50) not null,
	    PASSWORD varchar(50) not null,
	    ENABLED boolean not null
	);
	
	CREATE TABLE AUTHORITIES (
		Id int	not null AUTO_INCREMENT primary key ,
	    user_name varchar(50) not null,
	    AUTHORITY varchar(50) not null
	    --constraint fk_authorities_users foreign key(user_name) references USERS(user_name)
	);
	CREATE UNIQUE INDEX ix_auth_username on AUTHORITIES (user_name,AUTHORITY);

* As I'm using MySQL so above will work fine.
* Now we need to insert users and their roles, for this we need to create data.sql file:

	INSERT INTO USERS (user_name, PASSWORD, ENABLED) 
	VALUES('Az','Az',true);
	
	INSERT INTO USERS (user_name, PASSWORD, ENABLED) 
		VALUES('Adi','Adi',true);
	
	INSERT INTO USERS (user_name, PASSWORD, ENABLED) 
		VALUES('Shah','Shah',true);
	
	INSERT INTO USERS (user_name, PASSWORD, ENABLED) 
		VALUES('Mughal','Mughal',true);
	
	INSERT INTO AUTHORITIES (user_name, AUTHORITY)
		VALUES('Az','ROLE_USER');
	
	INSERT INTO AUTHORITIES (user_name, AUTHORITY)
		VALUES('Adi','ROLE_ACCOUNT');
		
	INSERT INTO AUTHORITIES (user_name, AUTHORITY)
		VALUES('Shah','ROLE_ADMIN');
		
	INSERT INTO AUTHORITIES (user_name, AUTHORITY)
		VALUES('Mughal','ROLE_ADMIN');

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
* In 7th example I will use Spring security with JWT for Authentication (Users) and Authorization (Roles).