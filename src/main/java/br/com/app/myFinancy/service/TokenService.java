package br.com.app.myFinancy.service;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class TokenService {

    @Value("dGVzdGUgZGUgc2VndXJhbsOnYSBzcHJpbmc=")
    private String signatureKey;

    public String createToken(UserDetails users) {
        Date today = new Date();
        Date expiration = new Date(today.getTime() + 1800000);
        return Jwts
                .builder()
                .setIssuedAt(today)
                .setExpiration(expiration)
                .setSubject(users.getUsername())
                .signWith(SignatureAlgorithm.HS512, signatureKey)
                .compact();
    }

    public Boolean isValidToken(String token) {
        try {
            Claims claims = findClaims(token);
            Date dateExpiration = claims.getExpiration();
            LocalDateTime localDateTime = dateExpiration.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            return !LocalDateTime.now().isAfter(localDateTime);
        } catch (Exception e) {
            return false;
        }
    }
    private Claims findClaims(String token) {
        return Jwts
                .parser()
                .setSigningKey(signatureKey)
                .parseClaimsJws(token)
                .getBody();
    }

    public String findLoginUser(String token) {
        return findClaims(token).getSubject();
    }
}
