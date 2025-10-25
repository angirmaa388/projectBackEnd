package com.example.productPromotion.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

//this class will privide authenticate an existing user
@RestController
@RequestMapping(path = "api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/Register")
    public ResponseEntity<AuthenticationResponse> register (
        @RequestBody RegisterRequest request
    ){
       return ResponseEntity.ok(service.register(request));
    }
    @PostMapping("/Authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate (
        @RequestBody AuthenticationRequest request
    ){
        return ResponseEntity.ok(service.authenticate(request));
    }

}
