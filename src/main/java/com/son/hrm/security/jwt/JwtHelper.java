package com.son.hrm.security.jwt;

import java.util.Date;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtHelper {
	
	private Logger logger = Logger.getLogger(getClass().getName());
	
	private String key = "whatissecret";
	private String prefix = "Bearer ";
	private long exp = 8500000;
	
	public String generateJwtToken(String email) {
		Date date = new Date();
		
		return Jwts.builder()
				   .setSubject(email)
				   .setIssuedAt(date)
				   .setExpiration(new Date(date.getTime() + exp))
				   .signWith(SignatureAlgorithm.HS512, key)
				   .compact();
	}
	
	public boolean validate(String token) {
		try {
			Jwts.parser().setSigningKey(key).parseClaimsJws(token);
			
			return true;
		} catch (UnsupportedJwtException e1) {
			logger.warning("Jwt is not supported.");
		} catch (MalformedJwtException e2) {
			logger.warning("Invalid token.");
		} catch (SignatureException e3) {
			logger.warning("Invalid signature.");
		} catch (ExpiredJwtException e4) {
			logger.warning("Jwt is unexpired.");
		} catch (IllegalArgumentException  e5) {
			logger.warning("ClaimsJws string is null");
		}
		
		return false;
	}
	
	public String getToken(HttpServletRequest request) {
		String jwt = request.getHeader("Authorization");
		
		if (jwt == null)
			return jwt;
		
		return jwt.substring(prefix.length(), jwt.length());
	}
	
	public String getEmail(String token) {
		return Jwts.parser()
				   .setSigningKey(key)
				   .parseClaimsJws(token)
				   .getBody()
				   .getSubject();
	}
}
