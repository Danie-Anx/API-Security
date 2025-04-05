package br.com.fiap.apisecurity.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.bind.annotation.PostMapping;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Bean
    public SecurityConfiguration securityConfiguration(HttpSecurity httpSecurity)
            throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(authorize -> authorize)
                    //
                .build();
    }

    @PostMapping("/register")
    public ResponseEntity

}
