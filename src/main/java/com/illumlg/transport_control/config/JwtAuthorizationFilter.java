package com.illumlg.transport_control.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
    private final String jwtSecret;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, String jwtSecret) {
        super(authenticationManager);
        this.jwtSecret = jwtSecret;
    }

    private UsernamePasswordAuthenticationToken getAuthenticationFromToken(HttpServletRequest request) {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (token == null || !token.startsWith("Bearer "))
            return null;
        try {
            Jws<Claims> claimsJws = Jwts.parserBuilder()
                    .setSigningKey(jwtSecret.getBytes())
                    .build().parseClaimsJws(token.replace("Bearer ", ""));
            String username = claimsJws.getBody().getSubject();
            if (username == null) {
                return null;
            }
            return new UsernamePasswordAuthenticationToken(username, null
                    , List.of(AuthorityContainer.userAuthorities.get(username)));
        } catch (JwtException exception) {
            System.out.printf("Some exception : %s failed : %s%n", token, exception.getMessage());
        }
        return null;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        UsernamePasswordAuthenticationToken authentication = getAuthenticationFromToken(request);
        if (authentication != null) {
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } else {
            SecurityContextHolder.clearContext();
        }
        chain.doFilter(request, response);
    }
}
