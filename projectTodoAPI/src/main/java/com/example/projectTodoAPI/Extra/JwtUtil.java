package com.example.projectTodoAPI.Extra;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256); // Ensure it matches what you used to generate JWT.


    public String generateToken(Long userId) {
        return Jwts.builder()
                .claim("userId", userId) // Add the userId as a claim
                .setIssuedAt(new Date(System.currentTimeMillis())) // Set issued date
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // Expiration: 10 hours
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY) // Use secret key to sign the token
                .compact();
    }


    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false; // Invalid token
        }
    }


    public Long extractUserId(String token) {
        Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        return Long.parseLong(claims.get("userId").toString()); // Ensure userId is extracted correctly
    }
}