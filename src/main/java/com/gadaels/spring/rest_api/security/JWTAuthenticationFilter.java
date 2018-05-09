package com.gadaels.spring.rest_api.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.naming.AuthenticationException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gadaels.spring.rest_api.models.ApplicationUser;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties.Jwt;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager=authenticationManager;

    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) 
        throws RuntimeException {
            try {
                ApplicationUser creds = new ObjectMapper()
                .readValue(req.getInputStream(), ApplicationUser.class);
                
                return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    creds.getUsername(), 
                    creds.getPassword(),
                    new ArrayList<>()));

            } catch (Exception e) {
                throw new RuntimeException(e);
            }

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, 
            HttpServletResponse response, 
            FilterChain chain,
            Authentication authResult) throws IOException, ServletException {

                String Token = Jwts.builder()
                .setSubject(((User) authResult.getPrincipal()).getName())
                .setExpiration(new Date(System.currentTimeMillis()+SecurityConstants.EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET.getBytes())
                .compact();
                response.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_P_STRING +Token);
        super.successfulAuthentication(request, response, chain, authResult);
    }
}

