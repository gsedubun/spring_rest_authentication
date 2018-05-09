package com.gadaels.spring.rest_api.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Jwts;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter{
    
    public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        //super.doFilterInternal(request, response, chain);
        String header = request.getHeader(SecurityConstants.HEADER_STRING);

        if(header==null || !header.startsWith(SecurityConstants.TOKEN_P_STRING)){
            chain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken authenticationToken = getAuthentication(request);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken );
        chain.doFilter(request, response);
    }

	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(SecurityConstants.HEADER_STRING);
        if(token !=null){
            String user  = Jwts.parser()
            .setSigningKey(SecurityConstants.SECRET.getBytes())
            .parseClaimsJws(token.replace(SecurityConstants.TOKEN_P_STRING, ""))
            .getBody()
            .getSubject();

            if(user==null){
                return new UsernamePasswordAuthenticationToken(user,null, new ArrayList<>());
            }
            return null;
        }
        return null;
	}
}