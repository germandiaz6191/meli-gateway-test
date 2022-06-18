package com.co.meli.gateway.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import reactor.core.publisher.Mono;

public class JwtUtil {
	
	private static final String AUTHORITIES_KEY = "authorities";
	
	public static Mono<Authentication> getUser(String authToken, String secret) {
		final JwtParser jwtParser = Jwts.parser().setSigningKey(secret);
		final Jws<Claims> claimsJws = jwtParser.parseClaimsJws(authToken);
		final Claims claims = claimsJws.getBody();
		String user = claims.getSubject();
		
		@SuppressWarnings("unchecked")
		List<String> rolesMap = claims.get(AUTHORITIES_KEY, List.class);
		
		//get the role
		List<GrantedAuthority> authorities = new ArrayList<>();
		if(null != rolesMap) {
			for (String rolemap : rolesMap) {
				authorities.add(new SimpleGrantedAuthority(rolemap));
			}
		}

		return Mono.just(new UsernamePasswordAuthenticationToken(user, null, authorities));
	}
}
