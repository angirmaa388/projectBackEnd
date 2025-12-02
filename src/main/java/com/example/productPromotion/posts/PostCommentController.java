package com.example.productPromotion.posts;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.productPromotion.users.Users;
import com.example.productPromotion.users.UsersRepository;
/**
 *
 * @author angirmaa
 */

@CrossOrigin(origins={"http://127.0.0.1:6500", "http://localhost:6500"})
@RestController
@RequestMapping(path = "api/v1/postComment")

public class PostCommentController {
    private final PostCommentService postCommentService;

    @Autowired
    public PostCommentController(PostCommentService postCommentService) {
        this.postCommentService = postCommentService;
    }
    
    @Autowired 
    private PostsRepository postsRepository;

    @Autowired 
    private UsersRepository usersRepository;

  
    
    @GetMapping("/getPostComment")
        public List<PostCommentResponse> gePostComments(@RequestParam Long postId) {
           return postCommentService.getPostComment(postId);
        }

     @PostMapping("/newComment")
	public ResponseEntity<String> newPostComment(
        @RequestParam Long postId, 
        @RequestParam Long userId, 
        
        @RequestParam String commentText){
        Posts post = postsRepository.findById(postId).orElse(null);
        Users user = usersRepository.findById(userId).orElse(null);
        PostComment postComment = new PostComment();
        postComment.setCommentText(commentText);
        postComment.setPosts(post);
        postComment.setUsers(user);
        postComment.setCommentedDateTime(LocalDateTime.now()); 
        postCommentService.addNewPostComment(postComment);
         return ResponseEntity.ok("post submitted");

	}
    
}
