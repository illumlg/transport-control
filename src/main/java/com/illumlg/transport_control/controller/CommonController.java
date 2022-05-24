package com.illumlg.transport_control.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommonController {

    @GetMapping("/user")
    public String greetAuthorized(Authentication a) {
        return String.format("Greetings, %s", a.getName());
    }

    @GetMapping("/admin")
    public String greetAdmin(Authentication a) {
        return String.format("Greetings to you, administrator %s", a.getName());
    }

    @GetMapping("/")
    public String greetEveryone() {
        return "Hello and welcome to transport control api";
    }
}
