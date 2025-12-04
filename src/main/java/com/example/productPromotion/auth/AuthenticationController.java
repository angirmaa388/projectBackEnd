package com.example.productPromotion.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

//this class will privide authenticate an existing user
@RestController
@CrossOrigin(origins={"http://127.0.0.1:6500", "http://localhost:6500"})
//connection for the local host http 
@RequestMapping(path = "api/v1/auth")
// request mappint path 
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;
    //declaring the AuthenticationService class for the connection 

    @PostMapping("/Register")
    public ResponseEntity<AuthenticationResponse> register (
        @RequestBody RegisterRequest request
    ){
       return ResponseEntity.ok(service.register(request));
    } // new user resigtration post mapping 

    @PostMapping("/Authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate (
        @RequestBody AuthenticationRequest request
    ){
        return ResponseEntity.ok(service.authenticate(request));
    } // user authenticate log in post mapping 

}
