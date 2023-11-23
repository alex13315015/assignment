package com.ryan9025.board.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthenticationFailureHandler userLoginFailHandler;
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests((auth) -> auth
                .requestMatchers("/","/member/**","/css/**","/js/**","/images/**","/mail/**")
                .permitAll()
                .anyRequest()
                .authenticated())
                .formLogin((auth) -> auth
                        .loginPage("/member/login") //get
                        .usernameParameter("id")
                        .loginProcessingUrl("/member/login") //post
                        .defaultSuccessUrl("/board/list")
                        //.failureUrl("/member/login?error=true")
                        .failureHandler(userLoginFailHandler)
                        .permitAll()
                )
                .logout((auth) -> auth
                        .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                        .logoutSuccessUrl("/board/list")
                        .invalidateHttpSession(true)
                )
                .csrf((auth -> auth.disable()));
          return httpSecurity.build();
    }
}
