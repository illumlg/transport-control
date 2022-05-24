package com.illumlg.transport_control.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Value("${jwt.secret}")
    private String jwtSecret;
    @Value("${jwt.issuer}")
    private String jwtIssuer;
    @Value("${jwt.type}")
    private String jwtType;
    @Value("${jwt.audience}")
    private String jwtAudience;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .addFilter(new JwtAuthenticationFilter(authenticationManager(),
                        jwtAudience, jwtIssuer, jwtSecret, jwtType))
                .addFilter(new JwtAuthorizationFilter(authenticationManager(), jwtSecret))
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/docs*", "/swagger*/*").permitAll()
                .antMatchers("/user").authenticated()
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/*/deliveries",
                        "/*/createCargoDelivery", "/*/createPassengerDelivery").authenticated()
                .antMatchers(HttpMethod.POST, "/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/**").authenticated()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().passwordEncoder(passwordEncoder())
                .withUser("user")
                .password(passwordEncoder().encode("1234")).roles("USER")
                .and().withUser("admin")
                .password(passwordEncoder().encode("1234")).roles("ADMIN");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
