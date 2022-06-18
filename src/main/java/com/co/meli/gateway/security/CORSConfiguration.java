package com.co.meli.gateway.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;

@Configuration
public class CORSConfiguration implements WebFluxConfigurer {
	
	@Bean
    public CorsWebFilter corsFilter() {
        return new CorsWebFilter(corsConfigurationSource());
    }
	
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(false)
                .allowedOrigins("http://ec2-3-88-62-17.compute-1.amazonaws.com")
                .allowedHeaders("*")
                .allowedMethods("*")
                .maxAge(180000l)
                .exposedHeaders(HttpHeaders.SET_COOKIE); 
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration().applyPermitDefaultValues();
        config.setAllowCredentials(false);
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        config.addAllowedOrigin("http://ec2-3-88-62-17.compute-1.amazonaws.com");
        config.setMaxAge(180000l);
        config.addExposedHeader(HttpHeaders.SET_COOKIE);
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}