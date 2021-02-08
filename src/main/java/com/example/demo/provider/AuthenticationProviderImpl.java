package com.example.demo.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.demo.domain.Authentication;
import com.example.demo.domain.User;
import com.example.demo.repository.UserRepo;

@Component
public class AuthenticationProviderImpl implements AuthenticationProvider {

	
	@Autowired
	UserRepo userRepo;
	
	@Override
	public Authentication authenticate(UsernamePasswordAuthenticationToken authReq) {
		Authentication authentication = new Authentication();
		
		 
		User user= userRepo.findByuserName(authReq.getName());
		if(user!=null)
		{
		   authentication.setUserName(user.getUserName());	
		   authentication.setPassword(user.getPassword());  
		}
		else
		{
			throw new UsernameNotFoundException("user Not found");
		}
		
		return authentication;
	}

}
