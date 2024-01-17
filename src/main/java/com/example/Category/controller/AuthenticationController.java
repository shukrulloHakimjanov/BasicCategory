package com.example.Category.controller;


import com.example.Category.model.user.auth.AuthenticationRequest;
import com.example.Category.model.user.auth.AuthenticationResponse;
import com.example.Category.model.user.auth.RegisterRequest;
import com.example.Category.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthenticationController {
    private final AuthenticationService service;

    @Operation(summary = "Post register", description = "Post register")
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@Valid @RequestBody RegisterRequest request) {
        log.info("REST request to register ");
        return ResponseEntity.ok(service.register(request));
    }

    @Operation(summary = "Post token", description = "Post token")
    @PostMapping("/refresh-token")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        service.refreshToken(request, response);
    }

    @Operation(summary = "Post auth", description = "Post auth")
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authentication(@RequestBody AuthenticationRequest request) {
        log.info("REST request to authentication ");
        return ResponseEntity.ok(service.authentication(request));
    }
}