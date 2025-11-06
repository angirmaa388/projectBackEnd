package com.example.productPromotion.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;

import com.example.productPromotion.users.UsersRepository;

import lombok.RequiredArgsConstructor;
/**
 *
 * @author angirmaa
 */

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {
    private final UsersRepository repository;

   
    @Bean
    public UserDetailsService userDetailsService(){
        return userName -> repository.findByEmail(userName)
        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Bean
    // this will fetch the user detials 
    //like password user detial
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider =new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;

    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
        return config.getAuthenticationManager();

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
 
}
