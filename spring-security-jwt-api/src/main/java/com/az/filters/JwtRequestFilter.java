package com.az.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.az.services.JwtUtil;
import com.az.services.MyUserDetailsService;

@Component
public class JwtRequestFilter extends OncePerRequestFilter{

	@Autowired
	private MyUserDetailsService userDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
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

}
