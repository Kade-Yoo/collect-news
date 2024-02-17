package com.example.application.config.security;

import com.example.application.config.jwt.JwtAuthorizationFilter;
import com.example.application.config.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtProvider jwtProvider;
    private final CustomAuthenticationProvider authenticationProvider;

    @Autowired
    public SecurityConfig(JwtProvider jwtProvider, CustomAuthenticationProvider authenticationProvider) {
        this.jwtProvider = jwtProvider;
        this.authenticationProvider = authenticationProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .httpBasic(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .sessionManagement(it -> it.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(it -> it
                        .requestMatchers("/", "/images/**","/css/**","/fonts/**","/h2-console/**","/favicon.ico", "/api/login","/login", "/join", "/logout").permitAll()
                        .anyRequest().hasAnyRole("ADMIN")
                )
                .headers(it -> it.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
                .addFilterBefore(
                        new JwtAuthorizationFilter(jwtProvider),
                        UsernamePasswordAuthenticationFilter.class
                )
                .authenticationProvider(authenticationProvider)
                .build();
    }

//    @Bean
//    public InitializingBean initializingBean() {
//        // @Async(비동기) 호출인 상황에서 새로운 스레드가 생성되었을 때 SecurityContext를 만들어 줄지 선택하는 전략
//        // MODE_INHERITABLETHREADLOCAL로 했을 때 어디서 동작이 안되는걸까? Forbidden이 뜨는거보면 중간 필터에서 안되는 것 같은데.. 뭐지
////        return () -> SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
//        return () -> SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_THREADLOCAL);
//    }
}
