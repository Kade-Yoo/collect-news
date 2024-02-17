package com.example.application.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final JdbcCustomUserDetailsService jdbcCustomUserDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        CustomUserDetails user = jdbcCustomUserDetailsService.loadUserByUsername(username);

        return checkPassword(user, password);
    }

    private Authentication checkPassword(CustomUserDetails user, String rawPassword) {
        if (bCryptPasswordEncoder.matches(rawPassword, user.getPassword())) {
            return new UsernamePasswordAuthenticationToken(
                    user.getUsername(),
                    user.getPassword(),
                    user.getAuthorities()
            );
        }

        throw new BadCredentialsException("Bad Credentials");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return false;
    }
}
