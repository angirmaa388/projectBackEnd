package com.example.productPromotion.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.productPromotion.config.JwtService;
import com.example.productPromotion.users.Role;
import com.example.productPromotion.users.Users;
import com.example.productPromotion.users.UsersRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    //declare the user repository to 
    //take data from user database 
    private final UsersRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request){
        // here all the user register metods 
        //it will check the all the data 
        var user = Users.builder()
            .userName(request.getUserName())
            .email(request.getEmail())
            .password(passwordEncoder.encode(request.getPassword()))
            .role(Role.ROLE_USER)
            .build();
        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
            .token(jwtToken)
            .userId(user.getUserId())
            .build();
    }
    public AuthenticationResponse authenticate(AuthenticationRequest request){
        authenticationManager.authenticate(
            //it will check the all the mistakes 
            //like any wrong password or empty 
            new UsernamePasswordAuthenticationToken(
                request.getEmail(), 
                request.getPassword()
                )
        );
        //if the user name and password correct 
        //it create a token and send it back 
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        //when it gets the user it will generate the user token 
        //and send the authentication response 

                var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
            .token(jwtToken)
            .userId(user.getUserId())
            .build();
        
    }


}
