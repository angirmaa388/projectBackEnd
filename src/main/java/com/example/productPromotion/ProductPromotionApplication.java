package com.example.productPromotion;

import com.example.productPromotion.users.Users;
import java.util.List;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ProductPromotionApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductPromotionApplication.class, args);
	}
        @GetMapping
        public List<Users> hello() {
            return List.of(
                    new Users
                            (1L, 
                             "Angirmaa",
                             "angirmaa388@gmail.com",
                             "angirmaa123", 
                             "user"));
        }
        

}
