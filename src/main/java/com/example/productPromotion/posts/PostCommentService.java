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
public class PostCommentService {
    private final PostCommentRepository postCommentRepository;
     @Autowired
    public PostCommentService(PostCommentRepository postCommentRepository) {
        this.postCommentRepository = postCommentRepository;
    }
    
    public List<PostCommentResponse> getPostComment(Long postId) {
            return postCommentRepository.findAllByPosts_PostId(postId).stream().map(postComment -> new PostCommentResponse(
                postComment.getPostCommentId(),
                postComment.getCommentText(),
                postComment.getUsers().getUserName(),
                postComment.getCommentedDateTime() != null ? postComment.getCommentedDateTime().toString(): null
            )) //it will get the post comments by post id and user id 
                //then make them a list 
            .toList();
        }

    public PostComment addNewPostComment(PostComment postComment) {
        return postCommentRepository.save(postComment);
         }

    

    

    
    
}
