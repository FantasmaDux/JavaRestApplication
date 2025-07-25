package com.fantasmaDux.restApplication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http
                .csrf(csrf -> csrf.disable()) // <--- ОТКЛЮЧАЕТ CSRF
                .authorizeHttpRequests(
                auth -> auth.requestMatchers("/auth").permitAll()
                        .requestMatchers("/api/all").hasRole("admin")
                        .anyRequest().authenticated()
        ).sessionManagement(
                session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        ).oauth2ResourceServer(
                resourceServer -> resourceServer.jwt(
                        jwtConfigurer -> jwtConfigurer.jwtAuthenticationConverter(
                                keycloackAuthConverter()

                        )
                    )
                )
                .build();
    }

    private Converter<Jwt,? extends AbstractAuthenticationToken> keycloackAuthConverter() {
        var converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(
                new AuthoritiseConverter()
        );
        return converter;
    }
}
