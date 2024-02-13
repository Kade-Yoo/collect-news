package com.example.application.config.security;

import com.example.domain.member.dto.MemberResult;
import com.example.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JdbcCustomUserDetailsService implements UserDetailsService {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) {
        MemberResult memberResult = memberService.getMemberByEmail(username);
        return User.builder()
                .username(memberResult.getUsername())
                .password(passwordEncoder.encode(memberResult.getPassword()))
                .roles(memberResult.getRole())
                .build();
    }
}
