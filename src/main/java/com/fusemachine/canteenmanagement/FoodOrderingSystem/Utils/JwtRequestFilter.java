package com.fusemachine.canteenmanagement.FoodOrderingSystem.Utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fusemachine.canteenmanagement.FoodOrderingSystem.service.CustomUserDetailsService;
import com.fusemachine.canteenmanagement.FoodOrderingSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
	
	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	@Autowired
	private Jwtutil jwtUtils;


	private final ObjectMapper objectMapper;

	public JwtRequestFilter(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}


	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		final String authorizationHeader = request.getHeader("Authorization");
		String username = null;
		String jwt = null;
	
		if(authorizationHeader!=null && authorizationHeader.contains("Bearer ")) {
			jwt = authorizationHeader.substring(7);
			username = jwtUtils.extractUsername(jwt);

			//setting username to global variable
		}
		
			if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
				UserDetails userDetails =userDetailsService.loadUserByUsername(username);
				System.out.println("Spring logoing user");
				
				if(jwtUtils.validateToken(jwt, userDetails)) {
					UsernamePasswordAuthenticationToken usernametoken =
							new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
					usernametoken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(usernametoken);
				}
				
			}
			
		filterChain.doFilter(request, response);
		
	}

}
