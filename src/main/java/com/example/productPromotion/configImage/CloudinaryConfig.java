package com.example.productPromotion.configImage;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudinary.Cloudinary;

@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary cloudinary(){
         Map<String, Object> config = new HashMap<>();
        config.put("cloud_name", "dyb8yf1ut");
        config.put("api_key", "194487337284958");
         config.put("api_secret", "zGZ6_meYvPaNJoWSVSRX1Wu3qWs");
        return new Cloudinary(config);
        
    }

}
