package com.connectSphere.APIGateway.filter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

@Service
public class JwtService {

    @Value("${jwt.secretkey}")
    private String jwtSecretkey;

    public SecretKey getSecretkey() {
        return Keys.hmacShaKeyFor(jwtSecretkey.getBytes(StandardCharsets.UTF_8));
    }

    public String getUserIdFromToken(String token) {
        final var claims = Jwts.parser()
                               .verifyWith(getSecretkey())
                               .build()
                               .parseSignedClaims(token)
                               .getPayload();

        return claims.getSubject();
    }
}
