package com.example.demo.utility;

import java.util.Date;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.example.demo.domain.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenProvider {

	@Value("${jwt.secreatKey}")
	private String secretKey;

	@Value("${jwt.expirationTime}")
	private String expirationTime;

	public String generateToken(User userPrinciple) {
		// String[] claims = getClaimsFromUser(userPrinciple);
		return Jwts.builder().setSubject(userPrinciple.getUserName()).setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + 3600000))
				.signWith(SignatureAlgorithm.HS512, secretKey).compact();
	}

	/*
	 * private Algorithm HmacMD5(byte[] bytes) { // TODO Auto-generated method stub
	 * return null; }
	 * 
	 * public List<GrantedAuthority> getAuthorities(String Token) {
	 * 
	 * String[] claims = getClaimsFromToken(); Stream<String> stream1 =
	 * Arrays.stream(claims); return
	 * stream1.map(SimpleGrantedAuthority::new).collect(Collectors.toList());
	 * 
	 * }
	 * 
	 * private String[] getClaimsFromToken() { // TODO Auto-generated method stub
	 * return null; }
	 */

	/*
	 * private String[] getClaimsFromUser(UserPrinciple user) { // TODO
	 * Auto-generated method stub List<String> authorities = new ArrayList<>();
	 * 
	 * for (GrantedAuthority claims : user.getAuthorities()) {
	 * authorities.add(claims.getAuthority()); } return authorities.toArray(new
	 * String[0]);
	 * 
	 * }
	 */

	public boolean isTokenValid(User userDetals, String token) {
		// TODO Auto-generated method stub
		String username = this.getUsernameFromToken(token);
		return (username.equals(userDetals.getUserName()) && isTokenExpired(token));

	}

	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	/**
	 * @param token
	 * @return
	 */
	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	public String getUsernameFromToken(String token) {
		// TODO Auto-generated method stub
		return getClaimFromToken(token, Claims::getSubject);

	}

	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
	}

}
