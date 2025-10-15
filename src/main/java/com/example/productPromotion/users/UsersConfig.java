/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.productPromotion.users;

import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 *
 * @author angirmaa
 */
@Configuration

public class UsersConfig {
    @Bean
    CommandLineRunner commandLineRunner(UsersRepository repository){
        return args -> {
            
            if(repository.count() == 0) {
            Users Angirmaa = new Users(
                    
                    "Angirmaa",
                    "angirmaa388@gmail.com",
                    "Angirmaa123",
                    "user"
            );
            Users Adam = new Users(
                    "Adam",
                    "angirmaa388@gmail.com",
                    "Angirmaa123",
                    "user"
            );
            repository.saveAll(List.of(Angirmaa,Adam)
            );
            
            }
    }; 
    }
    
}
