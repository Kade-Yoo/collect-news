package com.example.application.api.login.usecase;

import com.example.application.api.login.dto.LoginRequest;
import com.example.application.config.jwt.Jwt;
import com.example.application.config.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class LoginUsecase {


    private final AuthenticationProvider authenticationProvider;
    private final JwtProvider jwtProvider;

    @Autowired
    public LoginUsecase(AuthenticationProvider authenticationProvider, JwtProvider jwtProvider) {
        this.authenticationProvider = authenticationProvider;
        this.jwtProvider = jwtProvider;
    }

    /**
     * 로그인
     * 1. email, password 검증
     * 2. email, password로 Authentication 객체 생성
     * 3. JWT 생성
     */
    public Jwt login(LoginRequest request) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(request.email(), request.password());
        Authentication authentication = authenticationProvider.authenticate(authenticationToken);
        return jwtProvider.generateToken(authentication);
    }
}
