/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.productPromotion.posts;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author angirmaa
 */
@Service
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
    
   
} 
