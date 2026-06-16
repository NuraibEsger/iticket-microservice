package com.iticket.authentication.service;

import com.iticket.authentication.dao.entity.ERole;
import com.iticket.authentication.dao.entity.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class JwtService {
    @Value("${jwt.secret-key}")
    private String secretKey;

    @Getter
    @Value("${jwt.access-token-expiration}")
    private long accessTokenExpiration;

    @Getter
    @Value("${jwt.refresh-token-expiration}")
    private long refreshTokenExpiration;

    public String generateToken(UserEntity userEntity) {
        return Jwts.builder()
                .subject(userEntity.getUsername())
                .claim("roles", userEntity.getRoles().stream()
                        .map(role -> role.getName().name())
                        .toList()
                )
                .claim("email", userEntity.getEmail())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + (accessTokenExpiration * 60 * 1000)))
                .signWith(getSigningKey())
                .compact();
    }

    public String generateRefreshToken(UserDetails userDetails) {
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .claim("type", "refresh")
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + (refreshTokenExpiration * 60 * 1000)))
                .signWith(getSigningKey())
                .compact();
    }

    public boolean isRefreshToken(String token) {
        return "refresh".equals(extractAllClaims(token).get("type", String.class));
    }

    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    public String extractEmail(String token) {
        return extractAllClaims(token).get("email", String.class);
    }

    public Set<ERole> extractRoles(String token) {
        Claims claims = extractAllClaims(token);

        Object roles = claims.get("roles");

        if (!(roles instanceof List<?> roleList)) {
            return Set.of();
        }

        return roleList.stream()
                .map(String::valueOf)
                .map(ERole::valueOf)
                .collect(Collectors.toSet());
    }

    public boolean isTokenValid(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private SecretKey getSigningKey()
    {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
    }
}
