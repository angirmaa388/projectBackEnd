package com.example.productPromotion.config;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.productPromotion.users.Users;

import jakarta.validation.Valid;

/**
 *
 * @author angirmaa
 */
@CrossOrigin(origins={"http://127.0.0.1:6500", "http://localhost:6500"})
// connection to the front end 
@RestController
@RequestMapping(path = "api/v1/auth")
public class UserLogOutController {

@PostMapping("/logOut")
//post mapping log out if user log out it will returm log out message 
        public ResponseEntity<String> logOut(){
            return ResponseEntity.ok("successfully logged out");
            
        }

}
