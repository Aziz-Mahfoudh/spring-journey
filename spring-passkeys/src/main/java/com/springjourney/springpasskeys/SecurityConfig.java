package com.springjourney.springpasskeys;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> auth.requestMatchers("/webauthn/register")
                            .permitAll()
                            .anyRequest()
                            .authenticated()
                )
                .formLogin(login -> login.defaultSuccessUrl("/"))
                .webAuthn(webauthn -> webauthn.rpName("Spring Passkeys")
                        .rpId("localhost")
                        .allowedOrigins("http://localhost:8080"))
                .logout(logout -> logout.logoutSuccessUrl("/login"))
                .build();
    }
}
