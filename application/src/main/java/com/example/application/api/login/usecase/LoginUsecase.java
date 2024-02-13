package com.example.application.api.login.usecase;

import com.example.application.config.jwt.Jwt;
import com.example.application.config.jwt.JwtProvider;
import com.example.application.api.login.dto.LoginRequest;
import com.example.domain.common.ErrorCode;
import com.example.domain.common.InvalidInputException;
import com.example.domain.member.service.MemberService;
import com.example.domain.member.dto.MemberResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginUsecase {

    private final MemberService memberService;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    @Autowired
    public LoginUsecase(MemberService memberService, AuthenticationManagerBuilder authenticationManagerBuilder, PasswordEncoder passwordEncoder, JwtProvider jwtProvider) {
        this.memberService = memberService;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
    }

    /**
     * 로그인
     *
     * 1. email, password 검증
     * 2. email, password로 Authentication 객체 생성
     * 3. JWT 생성
     */
    public Jwt login(LoginRequest request) {
        MemberResult memberResult = memberService.getMemberByEmail(request.email());
        if (!passwordEncoder.matches(request.password(), memberResult.getPassword())) {
            throw new InvalidInputException(ErrorCode.UNAUTHORIZED_ERROR);
        }

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(request.email(), memberResult.getPassword());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        return jwtProvider.generateToken(authentication);
    }
}
