package com.co.meli.gateway.security.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.co.meli.gateway.security.JwtUtil;

import io.jsonwebtoken.ExpiredJwtException;
import reactor.core.publisher.Mono;

@Component
public class AuthenticationManager implements ReactiveAuthenticationManager {

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationManager.class);
	
	@Value("${spring.security.encode.secret}")
    private String secret;
		
	@Override
	public Mono<Authentication> authenticate(Authentication authentication) {
		String authToken = authentication.getCredentials().toString();
		try {
			return JwtUtil.getUser(authToken, secret);
		} catch (ExpiredJwtException e) {
			LOGGER.error("Error autenticando, token no valido: ".concat(e.toString()));
			return Mono.just(authentication);
		} catch (Exception e) {
			LOGGER.error("Error autenticando: ".concat(e.toString()));
			return Mono.empty();
		}
	}
}
