package com.example.productPromotion.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;

import com.example.productPromotion.users.UsersRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {
    private final UsersRepository repository;
    @Bean
    public UserDetailsService ussUserDetailsService(){
        return userName -> repository.findByEmail(userName)
        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

}
