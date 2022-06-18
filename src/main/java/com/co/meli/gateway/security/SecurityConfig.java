package com.co.meli.gateway.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.savedrequest.NoOpServerRequestCache;

import com.co.meli.gateway.security.services.AuthenticationManager;
import com.co.meli.gateway.security.services.SecurityContextRepository;

import reactor.core.publisher.Mono;


@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class SecurityConfig {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private SecurityContextRepository securityContextRepository;
	
    @Bean
    public SecurityWebFilterChain securitygWebFilterChain(ServerHttpSecurity http) throws Exception{
		    return http.cors().and()
		            .exceptionHandling()
		            .authenticationEntryPoint((swe, e) -> Mono.fromRunnable(() -> {
		                swe.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
		            })).accessDeniedHandler((swe, e) -> Mono.fromRunnable(() -> {
		                swe.getResponse().setStatusCode(HttpStatus.NON_AUTHORITATIVE_INFORMATION);
		            })).and()
		            .csrf().disable()
		            .httpBasic().disable()
		            	.authenticationManager(authenticationManager)
		            	.securityContextRepository(securityContextRepository)
		            	.authorizeExchange()
		            .and().requestCache().requestCache(NoOpServerRequestCache.getInstance())//Elimina autenticaci{on cache
		            .and().authorizeExchange()
		            .pathMatchers("/api/**").authenticated()
		            .pathMatchers(HttpMethod.OPTIONS).permitAll()
		            .pathMatchers(HttpMethod.POST).permitAll()		            
		            .anyExchange().permitAll()
		            .and().build();
    }
    
}
