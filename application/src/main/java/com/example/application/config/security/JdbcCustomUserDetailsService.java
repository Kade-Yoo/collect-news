package com.example.application.config.security;

import com.example.domain.member.dto.MemberResult;
import com.example.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JdbcCustomUserDetailsService implements UserDetailsService {

    private final MemberService memberService;

    @Override
    public CustomUserDetails loadUserByUsername(String username) {
        MemberResult memberResult = memberService.getMemberByEmail(username);
        return new CustomUserDetails(
                memberResult.getUsername(),
                memberResult.getPassword(),
                memberResult.getRole()
        );
    }
}
