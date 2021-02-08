package com.example.demo.constant;

public class SecurityConstant {

	public static final long EXPIRATION_TIME=72000;
	public static final String TOKEN_PREFIX="Bearer";
	public static final String JWT_TOKEN_HEADER="Jwt_Token";
	public static final String TOKEN_CANNOT_BE_VERIFIED="token can not be verified";
	public static final String GET_ARRAYS_LLC="Get Arrays LLC";
	public static final String GET_ARRAYS_ADMINISTRATOR="User Management Portal";
	public static final String AUTH="AUTH";
	public static final String FORBIDDEN_MESSAGE="need login to access the page";
	public static final String ACCESS_DENIED="You dont have permission";
	public static final String OPTIONS_HTTP_METHOD="OPTIONS";
	public static final String[] PUBLIC_URLS={"/v1/api/login","/v1/api/register","/user/resetpassword/**","/user/image/**"};
	public static final int OK = 200;
}
