package com.example.productPromotion.config;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
/**
 *
 * @author angirmaa
 */
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter{
    //extending this class OncePerRequestFilter supported from spring frame work
    //it'll be filter by one every request 

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
        // here all the parameters that will be supports 
        // JwtAuthenticationFilter
        @NonNull HttpServletRequest request, 
        @NonNull HttpServletResponse response, 
        @NonNull FilterChain filterChain) 
        // this filter chain contains all the other filters 
        // that will be used
            throws ServletException, 
            IOException {
                final String authHeader = request.getHeader("Authorization");
                // it takes the Authorization from header 
                //it will retreive the info from token
                final String jwt;
                // this will checking the jwt token 
                final String userEmail;
                //it will help extract the user information 
                //it extracts jwt token 

                if(authHeader == null || !authHeader.startsWith("Bearer ")){
                    filterChain.doFilter(request, response);
                    return;
                }//it will check the header and Bearer if there no header or Bearer it will cancel the request
                jwt = authHeader.substring(7);
                //the authentication header number starts 7 
                // as Bearer with space it's seven 
                userEmail = jwtService.extractUserName(jwt);
                if(userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null ){
                    UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
                    //if the user email is not null, and take all the information from the Spring security user details service class
                    if(jwtService.isTokenValid(jwt, userDetails)){
                        UsernamePasswordAuthenticationToken authToken =new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                        ); // it will create a authentication token 
                        authToken.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request)
                        );
                        SecurityContextHolder.getContext().setAuthentication(authToken);
                    }
                } //and then user will enter the system and filter will go to the next step 
                filterChain.doFilter(request, response);   
        } 
}
