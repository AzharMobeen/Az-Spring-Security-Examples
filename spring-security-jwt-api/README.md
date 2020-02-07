# Spring-Security-JWT-Api (7th Example)

It's a simple spring boot project in which I'm using spring security with JWT

* Create simple spring boot project from https://start.spring.io/ and add only Web and Security module
* Then open this project into any IDE as maven project
* Create SpringSecurityConfigurer class and extends WebSecurityConfigurerAdaptor
* Now override configure method with (custom class) myUserDetailsService class reference.
* Create MyUserDetailsService class and impletment UserDetailsService class.
* Now override loadUserByUsername method in whihc you can configure in memory user object.

# JWT Configuration
* Add JWT dependencies, It's a library that can allow us to create JWT and validate JWT.

		<dependency>
		    <groupId>io.jsonwebtoken</groupId>
		    <artifactId>jjwt</artifactId>
		    <version>0.9.1</version>
		</dependency> 
* For couple of handy methods we need to JwtUtil class in which we'll create useful methods
* Now create /authenticate API end point where we'll post user name and password then get JWT if valid.
* JwtUtil will handle jwt related things.
* Now need to override bellow method for /authenticate uri:

		@Override
		protected void configure(HttpSecurity http) throws Exception {		
			http.csrf().disable().authorizeRequests().antMatchers("/authentication").permitAll().anyRequest().authenticated().
			and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);					
		}
* it will allow to access /authentication uri but all others needs to be provide username and password.
* In latest Spring Boot version we cann't autowired AuthenticationManager

		@Autowired
		private AuthenticationManager authenticationManager;
* Now we have two options override and Define bean

		@Override
		@Bean
		public AuthenticationManager authenticationManagerBean() throws Exception {
			return super.authenticationManagerBean();
		}
* Or second option is to create custom authentication provider by implementing AuthenticationProvider (please check MyAuthenticationProvider)
* Not hit [/authentication](http://localhost:8080/authentication) with username/password. Result will be jwt.
* Now we will hit [/hello](http://localhost:8080/hello) with Authorization in header and value will be Bearer jwt.
* We need filter per request in which we will read header of a request and if it has valid jwt then we'll set spring security context.
	
		@Override
		protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
				throws ServletException, IOException {
			// In every request need to set Authorization in header with starts value "Bearer "+jwt.
			final String authorizationHeader = request.getHeader("Authorization");
			String userName = null;
			String jwt = null;
			if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
				jwt = authorizationHeader.substring(7);
				userName = jwtUtil.extractUsername(jwt);
			}

			if(userName != null && SecurityContextHolder.getContext().getAuthentication()==null) {
				UserDetails userDetails = this.userDetailsService.loadUserByUsername(userName);
				if(jwtUtil.validateToken(jwt, userDetails)) {
					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
							userDetails.getUsername(),null,userDetails.getAuthorities());
					usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				}
			}
			filterChain.doFilter(request, response);
		}   

* Inject this filter in every request, please update bellow method.
	
		@Override
		protected void configure(HttpSecurity http) throws Exception {		
			http.csrf().disable().authorizeRequests().antMatchers("/authentication").permitAll().anyRequest().authenticated().
			and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

			http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);		
		}
* Thats it :)


### Next Example:
* [In 8th example I will Spring-Security with AOuth 2.](/spring-security-oAuth-2.0)

