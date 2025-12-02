package com.example.productPromotion.auth;

import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.productPromotion.config.JwtService;
import com.example.productPromotion.config.Token;
import com.example.productPromotion.config.TokenRepository;
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
    private final TokenRepository tokenRepository;

    public AuthenticationResponse register(RegisterRequest request) {

        var registeredUser = repository.findByEmail(request.getEmail());
        if (registeredUser.isPresent()) {
            throw new IllegalStateException("Email is already used");
            
        }
       
        // here all the user register metods 
        //it will check the all the data 
        var user = Users.builder()
            .userName(request.getUserName())
            .email(request.getEmail())
            .password(passwordEncoder.encode(request.getPassword()))
            .status(request.getStatus())
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
            //Save the generated token 
            revokeAllTokenByUser(user);
            saveUserToken(user, jwtToken);

        return AuthenticationResponse.builder()
            .token(jwtToken)
            .userId(user.getUserId())
            .build();
        
    }
    private void revokeAllTokenByUser(Users user) {
        List<Token> validTokenListByUser = tokenRepository.findAllTokenByUser(user.getUserId());
        if(!validTokenListByUser.isEmpty()){
            validTokenListByUser.forEach(t->{
                t.setLoggedOut(true);
            });
        }
        tokenRepository.saveAll(validTokenListByUser);
    }
    private void saveUserToken(Users user, String jwtToken) {
        Token token = new Token();
        token.setToken(jwtToken);
        token.setLoggedOut(false);
        token.setUsers(user);
        tokenRepository.save(token);
    }


}
