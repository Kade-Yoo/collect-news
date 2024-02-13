package com.example.application.config.jwt;

import com.example.domain.common.AuthorizationExpiredException;
import com.example.domain.common.ErrorCode;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

import static com.example.application.config.constants.Constatns.AUTHORIZATION_HEADER;
import static com.example.application.config.constants.Constatns.BEARER_BY_TOKEN_PREFIX;
import static com.example.application.config.constants.Constatns.TOKEN_START_INDEX;

@Configuration
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends GenericFilterBean {
   private final JwtProvider jwtProvider;

   @Override
   public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
      HttpServletRequest httpRequest = (HttpServletRequest) request;
      String token = this.extractTokenByHeader(httpRequest);

      if (Objects.nonNull(token)) {
         if (jwtProvider.validateToken(token)) {
            if (this.jwtProvider.isExpired(token)) {
               throw new AuthorizationExpiredException(ErrorCode.AUTHORIZATION_EXPIRED_ERROR);
            }

            Authentication authentication = this.jwtProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
         }
      } else {
         if (httpRequest.getHeader("cookie") == null) {
            chain.doFilter(request, response);
            return;
         }

         if (httpRequest.getHeader("cookie").contains("Authorization")) {
            String jwtToken = httpRequest.getHeader("cookie").substring(21);
            Authentication authentication = this.jwtProvider.getAuthentication(jwtToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
         }
      }

      chain.doFilter(request, response);
   }

   private String extractTokenByHeader(HttpServletRequest request) {
      String token = request.getHeader(AUTHORIZATION_HEADER);
      if (StringUtils.hasText(token)) {
         if (token.startsWith(BEARER_BY_TOKEN_PREFIX)) {
            return token.substring(TOKEN_START_INDEX);
         }
      }

      return null;
   }
}
