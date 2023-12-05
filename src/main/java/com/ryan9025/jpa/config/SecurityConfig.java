package com.ryan9025.jpa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests((auth) -> auth
                .requestMatchers("/member/join","/member/login","/css/**","/js/**","/images/**")
                .permitAll()
                .anyRequest()
                .authenticated())
                .formLogin((auth) -> auth
                        .loginPage("/member/login")
                        .usernameParameter("userID")
                        .loginProcessingUrl("/member/login")
                        .defaultSuccessUrl("/board/pageList")
                        .permitAll()
                )
                .logout((auth) -> auth
                        .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true)
        )
                .csrf((auth) -> auth.disable());
        return httpSecurity.build();
    }
}
