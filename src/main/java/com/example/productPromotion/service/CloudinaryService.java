package com.example.productPromotion.service;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.cloudinary.Cloudinary;

import lombok.RequiredArgsConstructor;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@SuppressWarnings("unchecked")
public class CloudinaryService {
    @Autowired
    private Cloudinary cloudinary;
   
    public String uploadFile(MultipartFile file, String fileName) {
        try{
            Map<String, Object> result = cloudinary.uploader()
                                            .upload(file.getBytes(),
                                            Map.of("public_id", fileName,
                                            "resource_type", "auto")
                                            );

        return (String) result.get("secure_url");
        }catch(IOException e){
            throw new RuntimeException("Failed to upload file", e);
        }
    }
    

}
