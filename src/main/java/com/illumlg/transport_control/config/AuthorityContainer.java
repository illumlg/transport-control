package com.illumlg.transport_control.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.HashMap;
import java.util.Map;

public class AuthorityContainer {
    public static final Map<String, GrantedAuthority> userAuthorities = new HashMap<>();
    static {
        userAuthorities.put("admin", new SimpleGrantedAuthority("ROLE_ADMIN"));
        userAuthorities.put("user", new SimpleGrantedAuthority("ROLE_USER"));
    }
}
