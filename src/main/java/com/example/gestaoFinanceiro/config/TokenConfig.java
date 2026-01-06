package com.example.gestaoFinanceiro.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.gestaoFinanceiro.entity.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

import java.time.Instant;
import java.util.Date;

@Component
public class TokenConfig {


    @Value("${jwt.secret}")
    private String secret;



    public String generateToken(User user){
        Algorithm algorithm = Algorithm.HMAC256(secret);

        return JWT.create()
                .withClaim("UserName", user.getName())
                .withSubject(user.getEmail())
                .withExpiresAt(Date.from(Instant.now().plusSeconds(1000)))
                .withIssuedAt(Date.from(Instant.now()))
                .sign(algorithm);
    }

    public String extractUsername(String token){
        Algorithm algorithm = Algorithm.HMAC256(secret);

        return JWT.require(algorithm)
                .build()
                .verify(token)
                .getSubject();
    }


    public boolean isTokenValid(String token, UserDetails userDetails){
        String email = extractUsername(token);
        return  email.equals(userDetails.getUsername());
    }

}
