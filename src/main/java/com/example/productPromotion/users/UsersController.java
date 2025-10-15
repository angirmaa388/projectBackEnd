/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.productPromotion.users;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author angirmaa
 */
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
    
    @PostMapping
        public void registerNewUsers(@RequestBody Users user){
             usersService.addNewUsers(user);
        }
    
}
