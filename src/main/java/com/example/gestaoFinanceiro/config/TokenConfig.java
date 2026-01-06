package com.example.gestaoFinanceiro.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.gestaoFinanceiro.entity.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Component
public class TokenConfig {


    @Value("${jwt.secret}")
    private String secret;



    public String generateToken(User user){


        try{

            Algorithm algorithm = Algorithm.HMAC256(secret);

            String token = JWT.create()
                    .withClaim("UserName", user.getName())
                    .withSubject(user.getEmail())
                    .withExpiresAt(Date.from(genExpiration()))
                    .withIssuedAt(Date.from(Instant.now()))
                    .sign(algorithm);
                return token;

        }catch (JWTCreationException exception){
                throw new RuntimeException("Error while genereting token", exception);
        }

    }

    public Instant genExpiration(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

    public String validateToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.require(algorithm)
                    .build()
                    .verify(token)
                    .getSubject();

        }catch (JWTVerificationException exception){
            return "";
        }
    }

}
