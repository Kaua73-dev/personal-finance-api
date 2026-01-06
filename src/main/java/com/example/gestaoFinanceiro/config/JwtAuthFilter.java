package com.example.gestaoFinanceiro.config;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private TokenConfig tokenConfig;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

            String authHeader = request.getHeader("Authorization");


            if(authHeader != null && authHeader.startsWith("Bearer ")){
                String token = authHeader.substring(7);
                String email = tokenConfig.extractUsername(token);


                if(email != null && SecurityContextHolder.getContext().getAuthentication() == null){


                    UserDetails userDetails =
                            userDetailsService.loadUserByUsername(email);

                    if (tokenConfig.isTokenValid(token, userDetails)){
                        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                        SecurityContextHolder.getContext()
                                .setAuthentication(authentication);
                    }



                }

            }

            filterChain.doFilter(request, response);

    }
}






















