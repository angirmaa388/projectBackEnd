/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.productPromotion.users;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties.Authentication;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author angirmaa
 */
@CrossOrigin(origins="http://127.0.0.1:5500")
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
        public void registerNewUsers(@RequestBody @Valid Users user){
            System.out.println("Email:" + user.getEmail());
             usersService.addNewUsers(user);
        }
    @DeleteMapping(path = "{usersId}")
    public void deleteUser(@PathVariable("usersId") Long usersId){
        usersService.deleteUsers(usersId);
        
    }
    
    @PostMapping("/login")
    public ResponseEntity<Users>logIn(@RequestBody LogIn logIn){
        Users users = usersService.FindByEmail(logIn.getEmail());
        if(users != null && users.getPassword().equals(logIn.getPassword())){
            return ResponseEntity.ok(users);
        }else 
            return ResponseEntity.status(401).build();
        
    }
    

}