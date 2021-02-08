package com.example.demo.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.constant.SecurityConstant;
import com.example.demo.domain.User;
import com.example.demo.provider.AuthenticationProvider;
import com.example.demo.service.UserService;
import com.example.demo.utility.JwtTokenProvider;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {
     @Autowired
	JwtTokenProvider jwtTokenProvider;
     
     @Autowired
     UserService userService;
     
     @Autowired
     AuthenticationProvider authenticationProvider;
     
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		if (request.getMethod().equalsIgnoreCase(SecurityConstant.OPTIONS_HTTP_METHOD)) {
			response.setStatus(SecurityConstant.OK);
		}
		else
		{
			   String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
			   if(null==authHeader || !authHeader.startsWith(SecurityConstant.TOKEN_PREFIX))
			   {
				   filterChain.doFilter(request, response);
				   return;
			   }
			 String token=  authHeader.substring(SecurityConstant.TOKEN_PREFIX.length());
			 String username= jwtTokenProvider.getUsernameFromToken(token);
			 
			 if(username!=null  && SecurityContextHolder.getContext().getAuthentication()==null)
			 {
				//List<GrantedAuthority> grantedAuth= jwtTokenProvider.getAuthorities(token);
				 User userDetals = userService.loadByuserName(username);
				  
				  if (jwtTokenProvider.isTokenValid(userDetals, token))
				  {
					 // SecurityContextHolder.getContext().setAuthentication();
				  }
				
				
			 }
			 else
			 {
				 SecurityContextHolder.clearContext();
			 }
			 	 
		}
		
		filterChain.doFilter(request, response);
		  
	}

}
