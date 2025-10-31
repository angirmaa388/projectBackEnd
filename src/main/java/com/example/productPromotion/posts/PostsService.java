/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.productPromotion.posts;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;



/**
 *
 * @author angirmaa
 */
@Service
@SuppressWarnings("unchecked")
public class PostsService {
     private final PostsRepository postsRepository;

    
    @Autowired
    public PostsService(PostsRepository postsRepository) {
        this.postsRepository = postsRepository;
    }
    

    public List<PostResponse> getPosts() {
            return postsRepository.findAll().stream().map(posts -> new PostResponse(
                posts.getPostId(),
                posts.getPostText(),
                posts.getFilePath(),
                posts.getFileType(),
                posts.getUsers().getUserName(),
                posts.getPostedDateTime() != null ? posts.getPostedDateTime().toString(): null
            ))
            .toList();
        }

    public Posts addNewPosts(Posts posts) {
        return postsRepository.save(posts);
       }

       @Autowired
    private Cloudinary cloudinary;
   
    public String uploadFile(MultipartFile file, String fileName) {
        try{
            Map<String, Object> result = cloudinary.uploader()
                                            .upload(file.getBytes(),
                                            Map.of("public_id", fileName,
                                            "resource_type", "auto")
                                            );
        //     String OriginalUrl=(String) result.get("secure_url");
        
        //     // String resizeUrl=OriginalUrl.replace("/upload/", "/upload/w_600,h_800,c_fill/");

        // return resizeUrl;
        return (String) result.get("secure_url");

    }catch(IOException e){
            throw new RuntimeException("Failed to upload file", e);
        }
    }
    
   
} 
