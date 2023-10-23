package com.rogeriogregorio.bookmanagementsystem.services.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.rogeriogregorio.bookmanagementsystem.entities.UserEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(UserEntity userEntity) {

        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("book-management-system")
                    .withSubject(userEntity.getLogin())
                    .withExpiresAt(generateExpirationDate())
                    .sign(algorithm);
        } catch (JWTCreationException jwtCreationException) {
            throw new RuntimeException("Error while generating token", jwtCreationException);
        }
    }

    public String validateToken(String token) {

        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("book-management-system")
                    .build()
                    .verify(token)
                    .getSubject();
        }catch (JWTVerificationException jwtVerificationException) {
            return "";
        }
    }

    private Instant generateExpirationDate() {
        return LocalDateTime
                .now()
                .plusHours(2)
                .toInstant(ZoneOffset.of("-03:00"));
    }
}
