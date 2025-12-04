/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.productPromotion.users;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 *
 * @author angirmaa
 */
@CrossOrigin(origins={"http://127.0.0.1:6500", "http://localhost:6500"})
@RestController
@RequestMapping(path = "api/v1/users")
public class UsersController {
    private final UsersService usersService;
    @Autowired
    public UsersController(UsersService UsersService) {
        this.usersService = UsersService;
    }
    
    
    @GetMapping
        public List<Users> getUsers() {
           return usersService.getUsers();
        }

    @GetMapping("/{userId}")
    public UsersResponse getUser(@PathVariable Long userId) {
    Users user = usersService.getUserById(userId)
                             .orElseThrow(() -> new RuntimeException("User not found"));
    return new UsersResponse(
        user.getUserId(),
        user.getUserName(), 
        user.getEmail(), 
        user.getStatus());
}

    

}