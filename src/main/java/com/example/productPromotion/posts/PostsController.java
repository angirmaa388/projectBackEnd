/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.productPromotion.posts;

import com.example.productPromotion.users.Users;
import com.example.productPromotion.users.UsersRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

/**
 *
 * @author angirmaa
 */
@CrossOrigin(origins={"http://127.0.0.1:6500", "http://localhost:6500"})
@RestController
@RequestMapping(path = "api/v1/posts")
public class PostsController {
    private final PostsService postsService;
    @Autowired 
    private UsersRepository usersRepository;
    @Autowired
    public PostsController(PostsService postsService) {
        this.postsService = postsService;
    }
    
    
    @GetMapping
        public List<PostResponse> getPosts() {
           return postsService.getPosts();
            
   
        }

    @PostMapping
	public ResponseEntity<String> createPost(
                @RequestParam(value = "postText", required = false) String postText, 
                @RequestParam(value = "file",required = false) MultipartFile file,
                @RequestParam(value = "userId") Long userId,
			Posts posts) {
            
       

          if(file != null && !file.isEmpty()){
        posts.setFilePath(file.getOriginalFilename());
        posts.setFileType(file.getContentType());
 UriComponents uriComponents = ServletUriComponentsBuilder.fromCurrentContextPath()
     .path(posts.getFilePath()).buildAndExpand();
    String url = uriComponents.toUriString();
    posts.setFilePath(url);
        
        }else {
              
              posts.setFilePath(null);
              posts.setFileType(null);
          }
          posts.setPostText(postText);
         Users user = usersRepository.findById(userId).orElse(null);
         posts.setUsers(user);
         posts.setPostedDateTime(LocalDateTime.now());
         postsService.addNewPosts(posts);
         return ResponseEntity.ok("post submitted");
	}
     
  
}
