package com.example.productPromotion.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
/**
 *
 * @author angirmaa
 */
@Component
@RequiredArgsConstructor
public class CustomLogoutHandler implements LogoutHandler{
    private final TokenRepository tokenRepository;
    @Override
    public void logout(HttpServletRequest request, 
                    HttpServletResponse response, 
                    Authentication authentication) {
        final String authHeader = request.getHeader("Authorization");

                if(authHeader == null || !authHeader.startsWith("Bearer ")){
                    
                    return;
                } //it gets the header request from http then check the header Bearer 
               String jwt = authHeader.substring(7);
            
               Token storedToken = tokenRepository.findByToken(jwt).orElse(null);
               if(jwt!=null){//after it will check the token and if there is token it log out 
                storedToken.setLoggedOut(true);// set this true 
                tokenRepository.save(storedToken);
               }
               SecurityContextHolder.clearContext();//it will remove the logged out user from log in session 

        }

}
