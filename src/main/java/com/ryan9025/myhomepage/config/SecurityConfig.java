package com.ryan9025.myhomepage.config;

import com.ryan9025.myhomepage.service.CustomUserDetailsService;
import com.ryan9025.myhomepage.service.OAuth2DetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.UUID;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final OAuth2DetailsService oAuth2DetailsService;
    private final CustomUserDetailsService customUserDetailsService;
    @Bean
    public SecurityFilterChain filterChain (HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/","/auth/join","/auth/login","/mail/**","/css/**","/js/**","/images/**")
                        .permitAll()
                        .anyRequest()
                        .authenticated())
                .formLogin((form) -> form
                        .loginPage("/auth/login")
                        .usernameParameter("userID")
                        .passwordParameter("password")
                        .loginProcessingUrl("/auth/login")
                        .defaultSuccessUrl("/image/feed",true)
                        .permitAll()
                )
                .logout((logout) -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/auth/logout"))
                        .logoutSuccessUrl("/auth/login")
                        .invalidateHttpSession(true)
                )
                .rememberMe((auth) -> auth
                        .rememberMeParameter("saveMe")
                        .key(UUID.randomUUID().toString())
                        .userDetailsService(customUserDetailsService)
                        .tokenValiditySeconds(60*60*24*30)
                )
                .oauth2Login((oauth2Login) -> oauth2Login
                        .loginPage("/auth/login")
                        .defaultSuccessUrl("/")
                        .userInfoEndpoint((userInfo) -> userInfo
                                .userService(oAuth2DetailsService)
                        )
                )
                .csrf((csrf) -> csrf.disable());

        return httpSecurity.build();
    }
}
