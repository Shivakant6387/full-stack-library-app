package com.shivakant.springbootlibrary.config;

import com.okta.spring.boot.oauth.Okta;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.accept.ContentNegotiationStrategy;
import org.springframework.web.accept.HeaderContentNegotiationStrategy;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeRequests(auth ->
                        auth.requestMatchers("/api/books/secure/**").permitAll()
                                .requestMatchers("/api/books/**").permitAll()
                                .anyRequest().authenticated()
                ).oauth2ResourceServer().jwt();

            http.setSharedObject(ContentNegotiationStrategy.class,new HeaderContentNegotiationStrategy());
//        Okta.configureResourceServer401ResponseBody(http);
        return http.build();
    }
}
