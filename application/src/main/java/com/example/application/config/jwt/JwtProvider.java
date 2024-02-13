package com.example.application.config.jwt;

import com.example.domain.common.ErrorCode;
import com.example.domain.common.UnauthorizedException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.application.config.constants.Constatns.AUTH_CLAIM_NAME;

@Slf4j
@Component
public class JwtProvider {
   private final Key key;

   public JwtProvider() {
      byte[] decode = Decoders.BASE64.decode("2f3bc9dffa3b6fccdfc48a7b1e1cc767fc4825eea40a89bf7c94325f5daf253a");
      key = Keys.hmacShaKeyFor(decode);
   }

   /**
    * accessToken, refreshToken 생성
    *
    * @param authentication 인증 정보
    */
   public Jwt generateToken(Authentication authentication) {
      String authorities = authentication.getAuthorities().stream()
              .map(GrantedAuthority::getAuthority)
              .collect(Collectors.joining(","));

      long now = (new Date()).getTime();
      Date accessTokenExpiresIn = new Date(now + 600000L);
      String accessToken = Jwts.builder()
              .setSubject(authentication.getName())
              .claim(AUTH_CLAIM_NAME, authorities)
              .setExpiration(accessTokenExpiresIn)
              .signWith(key, SignatureAlgorithm.HS256)
              .compact();

      String refreshToken = Jwts.builder()
              .setExpiration(new Date(now + 86400000))
              .signWith(key, SignatureAlgorithm.HS256)
              .compact();

      return new Jwt("Bearer", accessToken, refreshToken);
   }

   /**
    * Jwt 인증 정보 조회
    *
    * @param token 토큰
    * @return 인증 정보
    */
   public Authentication getAuthentication(String token) {
      Claims claims = this.parseClaims(token);
      if (claims.get(AUTH_CLAIM_NAME) == null) {
         throw new UnauthorizedException(ErrorCode.UNAUTHORIZED_ERROR);
      }

      List<SimpleGrantedAuthority> authorities = Arrays.stream(Arrays.stream(claims.get("authority").toString().split(",")).dropWhile(String::isEmpty).toArray())
              .map(it -> new SimpleGrantedAuthority(it.toString()))
              .collect(Collectors.toList());

      UserDetails principal = new User(claims.getSubject(), "", authorities);
      return new UsernamePasswordAuthenticationToken(principal, "", authorities);
   }


   public boolean validateToken(String token) {
      try {
         Jwts.parserBuilder()
                 .setSigningKey(key)
                 .build()
                 .parseClaimsJws(token);
         return true;
      } catch (SecurityException | MalformedJwtException var3) {
         log.info("Invalid JWT", var3);
      } catch (ExpiredJwtException var5) {
         log.info("Expired JWT", var5);
      } catch (UnsupportedJwtException var6) {
         log.info("Unsupported JWT Token", var6);
      } catch (IllegalArgumentException var7) {
         log.info("JWT claims string is empty.", var7);
      }

      return false;
   }

   public boolean isExpired(String token) {
      Claims claims = this.parseClaims(token);
      Date claimsExpiration = claims.getExpiration();
      if (claimsExpiration == null) {
         throw new UnauthorizedException(ErrorCode.UNAUTHORIZED_ERROR);
      }

      return new Date().compareTo(claimsExpiration) > 0;
   }

   private Claims parseClaims(String token) {
      try {
         return Jwts.parserBuilder()
                 .setSigningKey(key)
                 .build()
                 .parseClaimsJws(token)
                 .getBody();
      } catch (ExpiredJwtException var4) {
         throw (new UnauthorizedException(ErrorCode.UNAUTHORIZED_ERROR));
      }
   }
}
