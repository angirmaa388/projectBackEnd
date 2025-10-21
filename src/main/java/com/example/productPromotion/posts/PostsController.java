/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.productPromotion.posts;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author angirmaa
 */
@CrossOrigin(origins="http://127.0.0.1:6500")
@RestController
@RequestMapping(path = "api/v1/posts")
public class PostsController {
    private final PostsService postsService;
    @Autowired
    public PostsController(PostsService postsService) {
        this.postsService = postsService;
    }
    
    
    @GetMapping
        public List<Posts> getPosts() {
           return postsService.getPosts();
        }

    @PostMapping
	public ResponseEntity<String> createPost(
                @RequestParam(value = "postText", required = false) String postText, 
                @RequestParam(value = "file",required = false) MultipartFile file,
			Posts posts) {
            
            
          if(file != null && !file.isEmpty()){
        posts.setFilePath(file.getOriginalFilename());
        posts.setFileType(file.getContentType());
        
        }else {
              posts.setPostText(postText);
          
          }
         postsService.addNewPosts(posts);
         return ResponseEntity.ok("post submitted");
	}
    
}
