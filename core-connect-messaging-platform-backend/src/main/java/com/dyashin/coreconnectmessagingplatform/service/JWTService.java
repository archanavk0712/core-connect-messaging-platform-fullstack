package com.dyashin.coreconnectmessagingplatform.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;


@Service
public class JWTService {

	@Value("${jwt.secret}")
	private String secretKey;
	
	@Value("${jwt.expiration}")
	private long jwtExpiration;
	
	private SecretKey getKey() {
		byte[] keyBytes= Decoders.BASE64.decode(secretKey);
		return Keys.hmacShaKeyFor(keyBytes);
	}
	
	public String generateToken(String username) {
		Map<String, Object> claims= new HashMap<>();
		return Jwts.builder()
				.addClaims(claims)
				.setSubject(username)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
				.signWith(getKey())
				.compact();
	}
	
	public boolean validateToken(String token, UserDetails userDetails) {
		String username=extractUsername(token);
		return username.equals(userDetails.getUsername()) && !isExpired(token);
	}
	
	public String extractUsername(String token) {
		return extractClaim(token, Claims :: getSubject);
	}
	
	private boolean isExpired(String token) {
		return extractExpiration(token).before(new Date());
	}
	
	private Date extractExpiration(String token) {
		return extractClaim(token, Claims:: getExpiration);
	}
	
	private <T>T extractClaim(String token, Function<Claims, T> resolver){
		return resolver.apply(extractAllClaims(token));
	}
	
	private Claims extractAllClaims(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(getKey())
				.build()
				.parseClaimsJws(token)
				.getBody();
	}
}
