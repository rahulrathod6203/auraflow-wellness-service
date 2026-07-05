package com.awp.auth.controller;

import com.awp.auth.dto.AuthResponse;
import com.awp.auth.dto.LoginDTO;
import com.awp.auth.dto.RegisterDTO;
import com.awp.auth.service.UserAuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin("http://localhost:5173")
@Tag(name = "Auth Controller", description = "Endpoints for User Login, Registration, and Logout") // Groups your APIs
public class AuthController {

    private final UserAuthService userAuthService;

    @PostMapping(value = {"/login", "/sign-in"})
    @Operation(summary = "Logs a user in", description = "Verifies credentials and initiates a user session.")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginDTO loginDTO) {
        AuthResponse successResponse = userAuthService.login(loginDTO);
        return ResponseEntity.ok(successResponse);
    }

    @PostMapping(value = {"/register", "/sign-up"})
    @Operation(summary = "Registers a new user", description = "Creates a new user profile with default USER access rights.")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterDTO registerDTO) {
        AuthResponse authResponse = userAuthService.register(registerDTO);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/api/v1/users/{email}")
                .buildAndExpand(registerDTO.email())
                .toUri();
        return ResponseEntity.created(location).body(authResponse);
    }

    @PostMapping("/logout")
    @Operation(summary = "Logs out the current user", description = "Clears the active context configuration session boundaries.")
    public ResponseEntity<Void> logout() {
        log.info("Processing logout request...");
        userAuthService.logout();
        return ResponseEntity.noContent().build();
    }
}