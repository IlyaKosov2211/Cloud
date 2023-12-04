package ru.netology.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

public class CloudConfigCors {

    @org.springframework.beans.factory.annotation.Value("${cors.credentials}")
    private Boolean credentials;

    @org.springframework.beans.factory.annotation.Value("${cors.origins}")
    private String origins;

    @org.springframework.beans.factory.annotation.Value("${cors.methods}")
    private String methods;

    @org.springframework.beans.factory.annotation.Value("${cors.headers}")
    private String headers;

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(credentials);
        configuration.setAllowedOrigins(List.of(origins));
        configuration.setAllowedMethods(List.of(methods));
        configuration.setAllowedHeaders(List.of(headers));
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}