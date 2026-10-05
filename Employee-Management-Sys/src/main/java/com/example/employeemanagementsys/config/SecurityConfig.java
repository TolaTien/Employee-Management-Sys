package com.example.employeemanagementsys.config;

import com.example.employeemanagementsys.entity.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/hello").permitAll() // cho phép truy cập /hello không cần login
                .requestMatchers("/users").permitAll()
                .requestMatchers("/register").permitAll()
                .requestMatchers("/error").permitAll() // cho phép xem thông tin lỗi thay vì bị chặn 403
                .anyRequest().authenticated()          // các endpoint khác vẫn cần login
            );

        return http.build();
    }
}
