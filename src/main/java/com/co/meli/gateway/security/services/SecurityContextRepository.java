package com.co.meli.gateway.security.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class SecurityContextRepository implements ServerSecurityContextRepository{
	
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SecurityContextRepository.class);

	@Autowired
	private AuthenticationManager authenticationManager;

	@Override
	public Mono<Void> save(ServerWebExchange swe, SecurityContext sc) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public Mono<SecurityContext> load(ServerWebExchange swe) {
		ServerHttpRequest request = swe.getRequest();
		HttpHeaders headers = request.getHeaders();
		 		 
		List<String> authHeader = headers.get(HttpHeaders.AUTHORIZATION);
		
		if (authHeader != null) {
			String authToken = authHeader.get(0);
			Authentication auth = new UsernamePasswordAuthenticationToken(authToken, authToken);
			return this.authenticationManager.authenticate(auth).map((authentication) -> {
				return new SecurityContextImpl(authentication);
			});
		} else {
			LOGGER.error("No existe token en la cabecera");
			return Mono.empty();
		}
	}
	
}
