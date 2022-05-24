package com.illumlg.transport_control.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final String jwtAudience;
    private final String jwtIssuer;
    private final String jwtSecret;
    private final String jwtType;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager,
                                   String jwtAudience, String jwtIssuer,
                                   String jwtSecret, String jwtType) {
        setAuthenticationManager(authenticationManager);
        setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/auth", "POST"));
        this.jwtAudience = jwtAudience;
        this.jwtIssuer = jwtIssuer;
        this.jwtSecret = jwtSecret;
        this.jwtType = jwtType;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            UserDTO credentials = new ObjectMapper().readValue(request.getInputStream(), UserDTO.class);
            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(
                            credentials.username(), credentials.password(), null));
        } catch (IOException e) {
            return getAuthenticationManager()
                    .authenticate(new UsernamePasswordAuthenticationToken(null, null, null));
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException {
        User user = (User)authResult.getPrincipal();
        SecretKey secretKey = Keys.hmacShaKeyFor(jwtSecret.getBytes());
        String token = Jwts.builder()
                .signWith(secretKey, SignatureAlgorithm.HS512)
                .setHeaderParam("typ", jwtType)
                .setIssuer(jwtIssuer)
                .setAudience(jwtAudience)
                .setSubject(user.getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000))
                .compact();
        response.getWriter().write("Bearer " + token);
        response.getWriter().flush();
    }
}
