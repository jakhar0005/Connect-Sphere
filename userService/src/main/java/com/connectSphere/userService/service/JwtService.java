package com.connectSphere.userService.service;

import com.connectSphere.userService.entity.User;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;

import java.util.Date;

import static io.jsonwebtoken.security.Keys.hmacShaKeyFor;

/**
 * Service class for handling JWT token generation and validation.
 */
@Service
public class JwtService {
    /**
     * The secret key used for signing JWT tokens.
     */
    @Value("${jwt.secretKey}")
    private String secretKey;

    /**
     * Retrieves the secret key used for signing JWT tokens.
     *
     * @return The secret key.
     */
    private SecretKey getSecretKey() {
        return hmacShaKeyFor(secretKey.getBytes());
    }

    /**
     * Generates an access token for the given user.
     *
     * @param user The user for whom the access token is to be generated.
     *
     * @return A JWT access token.
     */
    public String generateAccessToken(final User user) {
        return Jwts.builder()
            .subject(user.getId().toString())
            .claim("email", user.getEmail())
            .issuedAt(new Date())
            .expiration(new Date(System.currentTimeMillis() + 1000*60*100))
            .signWith(getSecretKey())
            .compact();
    }
}
