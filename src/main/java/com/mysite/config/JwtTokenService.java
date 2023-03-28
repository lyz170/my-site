package com.mysite.config;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;
import java.util.stream.Collectors;

@Slf4j
@Component
public class JwtTokenService {

    private static final long TOKEN_VALIDITY_IN_MILLISECONDS = 1000 * 60 * 60 * 24;

    private static final long TOKEN_VALIDITY_IN_MILLISECONDS_FOR_REMEMBER_ME = 1000 * 60 * 60 * 24 * 7;

    private String jwtSecretKey = "AeViQVgA2CdkFInGFjtOmz9jpX+GQtkn2j+ql0bVIMI=";

    public String generateToken(Authentication authentication, boolean rememberMe) {
        String authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        long now = new Date().getTime();
        Date validDate = new Date(now + (rememberMe ? TOKEN_VALIDITY_IN_MILLISECONDS_FOR_REMEMBER_ME : TOKEN_VALIDITY_IN_MILLISECONDS));
        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim("auth", authorities)
                .setExpiration(validDate)
                .setIssuer("my-site")
                .signWith(Keys.hmacShaKeyFor(Base64.getDecoder().decode(jwtSecretKey)))
                .compact();
    }
}
