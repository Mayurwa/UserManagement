package com.example.demo.filter;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.example.demo.constant.SecurityConstant;
import com.example.demo.domain.HttpResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler{

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpResponse httpResponse = new HttpResponse(HttpStatus.UNAUTHORIZED.value(), SecurityConstant.ACCESS_DENIED,
				HttpStatus.UNAUTHORIZED, HttpStatus.UNAUTHORIZED.getReasonPhrase());
		// logger.debug("Pre-authenticated entry point called. Rejecting access");
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.setStatus(httpResponse.getStatusCode());
		OutputStream outputStream = response.getOutputStream();
		ObjectMapper mapper= new ObjectMapper();
		mapper.writeValue(outputStream, httpResponse);
		outputStream.flush();
		
	}

}
