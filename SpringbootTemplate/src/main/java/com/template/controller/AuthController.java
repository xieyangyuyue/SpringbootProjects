package com.template.controller;

import com.template.pojo.User;
import com.template.security.JwtUtil;
import com.template.service.CustomUserDetailsService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Resource
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
    @PostMapping("/authenticate")
    public ResponseEntity<String> createAuthenticationToken(@RequestBody User user) throws Exception {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
        );

        final String jwt = jwtUtil.generateToken(user.getUsername());
        return ResponseEntity.ok(jwt);
    }
}