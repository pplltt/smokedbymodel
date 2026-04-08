package com.example.smokedbymodel.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/login", "/register").permitAll()
                .requestMatchers("/user").authenticated()
                .requestMatchers("/user/{userId}").authenticated()
                .requestMatchers("/home", "/reviews/**", "/brands/**").authenticated()
                .requestMatchers("/brands", "/brands/**").permitAll()
                .requestMatchers("/brands/*/reviews/new").authenticated()
                .anyRequest().authenticated()
        ).formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/home", true)
                .permitAll()
        ).logout(logout -> logout
                .logoutSuccessUrl("/login?logout")
                .permitAll()
        );
        return http.build();
    }
}
