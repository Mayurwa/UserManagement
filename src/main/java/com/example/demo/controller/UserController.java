package com.example.demo.controller;

import java.util.Objects;

import javax.management.relation.RoleNotFoundException;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.JwtResponse;
import com.example.demo.domain.LoginRequest;
import com.example.demo.domain.User;
import com.example.demo.exception.UserAlreadyPresentException;
import com.example.demo.provider.AuthenticationProvider;
import com.example.demo.service.UserService;
import com.example.demo.utility.JwtTokenProvider;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Mayur This Controller class is used to call service method
 * 
 */
@RestController
@RequestMapping("/v1/api")
public class UserController {

	/**
	 * Autowiring the user interface
	 */
	/**
	 * 
	 */
	@Autowired
	UserService userService;

	/**
	 * 
	 */
	@Autowired
	AuthenticationProvider authProvider;

	/**
	 * Autowired JwtTokenProvider
	 */
	@Autowired
	JwtTokenProvider jwtTokenProvider;

	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody User user) {
		if (Objects.nonNull(user)) {

			try {
				userService.register(user);
			} catch (UserAlreadyPresentException | RoleNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ResponseEntity.ok("user created succesfully");

	}

	@PostMapping(value = "/login")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest request) {
        
		UsernamePasswordAuthenticationToken authReq
		 = new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword(),null);
		Authentication authenticate = authProvider
				.authenticate(authReq);
		SecurityContext context = SecurityContextHolder.getContext();
		context.setAuthentication(authenticate);
		User user = userService.loadByuserName(request.getUserName());
		JwtResponse jwtResponse = new JwtResponse();
		String token = jwtTokenProvider.generateToken(user);
		if (null != token) {
			jwtResponse.setToken(token);
		}

	  if(user != null)
	  {
		  jwtResponse.setUser(user);
	  }

		return new ResponseEntity(jwtResponse, HttpStatus.OK);

	}
   
	@GetMapping("/user")
	public String getData()
	{
		System.out.println("in get");
		return "hello";
	}
}
