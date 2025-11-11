/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.productPromotion.users;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.productPromotion.posts.PostCommentResponse;

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
   

    
    @PostMapping
        public void registerNewUsers(@RequestBody @Valid Users user){
            System.out.println("Email:" + user.getEmail());
             usersService.addNewUsers(user);
        }
    @DeleteMapping(path = "{usersId}")
    public void deleteUser(@PathVariable("usersId") Long usersId){
        usersService.deleteUsers(usersId);
        
    }
    

}