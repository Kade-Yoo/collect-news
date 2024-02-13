package com.example.application.api.login.controller;

import com.example.application.api.login.dto.LoginRequest;
import com.example.application.api.login.usecase.LoginUsecase;
import com.example.application.config.jwt.Jwt;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private final LoginUsecase loginUsecase;

    @Autowired
    public LoginController(LoginUsecase loginUsecase) {
        this.loginUsecase = loginUsecase;
    }

    /**
     * 로그인 API
     *
     * @param request 로그인 정보
     */
    @PostMapping("/login")
    public ResponseEntity<Jwt> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(loginUsecase.login(request));
    }

    /**
     * 로그아웃 api
     *
     * @param request 로그인 정보
     */
    @PostMapping("/api/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if ("Authorization".equals(cookie.getName())) {
                cookie.setMaxAge(0);
                SecurityContextHolder.clearContext();
            }
        }

        return ResponseEntity.ok().build();
    }
}
