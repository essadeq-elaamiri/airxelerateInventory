package com.airxelerate.airxelerateInventory.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtUtil {
    @Value("${jwt.secret}")
    private String jwtSecret;
    @Value("${jwt.expiration}")
    private Long jwtExpiration;

    private SecretKey key;

    @PostConstruct
    public void init(){
        key = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(String email){
        Date now = new Date();
        Date exp = new Date(now.getTime() + jwtExpiration) ;

        return Jwts.builder()
                .signWith(key)
                .subject(email)
                .issuedAt(now)
                .expiration(exp)
                .compact();
    }

    public boolean validateToken(String token){
        try {
            Jwts.parser().verifyWith(key).build().parseSignedClaims(token);
            Date expirDate = (Date) extractEmailAndExpirationFromToken(token).get("exp");
            return !expirDate.before(new Date());
        }catch (Exception e){
            log.error("JWT token validation error [{}]", e.getMessage());
        }
        return false;
    }

    public Map<String, Object> extractEmailAndExpirationFromToken(String token){
        Map<String, Object> emailAndExpMap = new HashMap<>();
        Claims claims = Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();
        emailAndExpMap.put("email", claims.getSubject());
        emailAndExpMap.put("exp", claims.getExpiration());
        return emailAndExpMap;
    }
}
