package com.example.demo.provider;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import com.example.demo.domain.Authentication;

public interface AuthenticationProvider {

	Authentication authenticate(UsernamePasswordAuthenticationToken authReq);

}
