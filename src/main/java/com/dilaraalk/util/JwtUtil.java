package com.dilaraalk.util;


import java.util.Date;
import java.util.List;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    @Value("${jwt.secret:myVerySecretKeyForJWTTokenGeneration123456789012345678901234567890}")
    private String secretKey;
    
    @Value("${jwt.expiration:86400000}") // 24 saat (milisaniye)
    private Long expiration;
    
    // JWT Token üretme
    public String generateToken(String username, List<String> roles) {
        return Jwts.builder()
                .subject(username)
                .claim("roles", roles)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey())
                .compact();
    }
    
    // Token'dan username alma
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }
    
    // Token'dan roller alma
    @SuppressWarnings("unchecked")
    public List<String> getRolesFromToken(String token) {
        return getClaimFromToken(token, claims -> claims.get("roles", List.class));
    }
    
    // Token'dan expiration date alma
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }
    
    // Token'dan belirli bir claim alma
    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }
    
    // Token'dan tüm claims alma
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
    
    // Token süresinin dolup dolmadığını kontrol etme
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }
    
    // Token'ın geçerli olup olmadığını kontrol etme
    public Boolean validateToken(String token, String username) {
        final String tokenUsername = getUsernameFromToken(token);
        return (tokenUsername.equals(username) && !isTokenExpired(token));
    }
    
    // Token'ın genel geçerliliğini kontrol etme
    public Boolean isTokenValid(String token) {
        try {
            return !isTokenExpired(token);
        } catch (Exception e) {
            return false;
        }
    }
    
    // Signing key'i oluşturma
    private SecretKey getSignInKey() {
        byte[] keyBytes = secretKey.getBytes(); // Sadece direkt byte dizisine çevir
        return Keys.hmacShaKeyFor(keyBytes);
    }


    public String extractUsername(String token) {
        return getUsernameFromToken(token);
    }

   

	
}