package com.example.digitaltask.controller;

import com.example.digitaltask.model.*;
import com.example.digitaltask.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/userRegister")
    public ResponseEntity<RegisterResponse> userRegister(
            @Valid @RequestBody UserRegisterRequest userRegisterRequest) {
        return ResponseEntity.ok(authenticationService.useRegister(userRegisterRequest));
    }
    @PostMapping("/adminRegister")
    public ResponseEntity<RegisterResponse> adminRegister(
            @Valid @RequestBody AdminRegisterRequest adminRegisterRequest) {
        return ResponseEntity.ok(authenticationService.adminRegister(adminRegisterRequest));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest authenticationRequest) {
        return ResponseEntity.ok(authenticationService.authenticate(authenticationRequest));
    }

}
