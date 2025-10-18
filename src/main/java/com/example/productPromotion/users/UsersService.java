/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.productPromotion.users;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 *
 * @author angirmaa
 */
@Service 
public class UsersService {
    private final UsersRepository usersRepository;
    
    @Autowired
    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }
    
    
     public List<Users> getUsers() {
            return usersRepository.findAll();
        }

    public void addNewUsers(Users user) {
        Optional<Users> usersByEmail = usersRepository
                .findUsersByEmail(user.getEmail());
        if(usersByEmail.isPresent()){
            throw new IllegalStateException("email already used");
        }
        
        usersRepository.save(user);
        
        
        }

    public void deleteUsers(Long usersId) {
        boolean exists = usersRepository.existsById(usersId);
        if(!exists){
            throw new IllegalStateException("id does not exists");
        }
        usersRepository.deleteById(usersId);
        }

    Users FindByEmail(String email) {
        return usersRepository.findUsersByEmail(email)
                .orElseThrow(() -> new IllegalStateException("email doesn't exist"));
        }
    
}
