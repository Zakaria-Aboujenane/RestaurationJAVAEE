package com.isga.utils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtUtils {
	    private static final long EXPIRATION_TIME = 864_000_000; // 10 days
	    private static final Key KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	    public static final String AUTHENTICATION_SCHEME = "Bearer ";
    public static String generateToken(String subject) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        Date expiration = new Date(nowMillis + EXPIRATION_TIME);

        return Jwts.builder()
        		.claim("username",subject)
                .setSubject(subject)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(KEY)
                .compact();
    }

    public static Jws<Claims> validateToken(String token) {
        return Jwts.parserBuilder().setSigningKey(KEY).build().parseClaimsJws(token);
    }
}
