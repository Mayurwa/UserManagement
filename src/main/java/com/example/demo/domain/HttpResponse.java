package com.example.demo.domain;

import org.springframework.http.HttpStatus;

public class HttpResponse {
	private int statusCode;
	private String message;
	private HttpStatus httpStatus;
	private String reason;
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public HttpResponse(int statusCode, String message, HttpStatus httpStatus, String reason) {
		super();
		this.statusCode = statusCode;
		this.message = message;
		this.httpStatus = httpStatus;
		this.reason = reason;
	}
	
	public HttpResponse()
	{
		
	}

}
