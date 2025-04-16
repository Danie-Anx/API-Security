package br.com.fiap.apisecurity.service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt;
import org.springframework.stereotype.Service;

import br.com.fiap.apisecurity.model.User;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                .withIssuer("apisecurity")
                .withSubject(user.getUsername())
                .withExpiresAt(genExpirationInstant())
                .sing(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro na geração de token", exception)
        }
    }

    private Object genExpirationInstant() {
        return LocalDateTime
                    .now()
                    .plusMinutes(2)
                    .toInstant(ZoneOffset.of("-03:00"));
    }

    public String validateToken(String token) {
        try {
            Algotithm algotithm = Algotithm.HMAC256(secret);
            return JWT.require(algotithm)
                .withIssuer("apisecurity")
                .build()
                .verify(token)
                .getSubject();
        } catch (JWTVerificationException exception) {
            return "";
        }
    }



}
