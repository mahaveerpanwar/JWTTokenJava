package com.SpringsecurityJwtToken.util;

import java.util.Date;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import java.util.concurrent.TimeUnit;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

@Component
public class JWTUtil {

	@Value("${app.secret}")
	private String secret;
	
	@Value("${app.privateKey}")
    private String privateKey;
	

	public String generatetoken(String subject) {

		return Jwts.builder().setSubject(subject).setIssuer("mahaveer")
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(15)))
//				.signWith(SignatureAlgorithm.RS256, secret.getBytes())
				.signWith(SignatureAlgorithm.RS512, privateKey)
				.compact();
		
	}

	public Claims getClaims(String token) {

		return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
	}

	public Date getexpireDate(String token) {
		return getClaims(token).getExpiration();
	}

	public String getusername(String token) {
		return getClaims(token).getSubject();
	}
	
	public Boolean istokenexpire(String token) {
		Date expiredate= getexpireDate(token);
		 return	expiredate.before(new Date(System.currentTimeMillis()));
	}
	
	public boolean validateToken(String token,String Username) {
		String tokenusername= getusername(token);
		return (Username.equals(tokenusername)&& !istokenexpire(token));
	}
	
	
	
	
	
	
}
