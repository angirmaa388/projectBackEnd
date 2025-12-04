/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.productPromotion.posts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author angirmaa
 */
public interface PostCommentRepository 
        extends JpaRepository <PostComment, Long> {
    //this class will allow to server connect to data bas
                List<PostComment> findAllByPosts_PostId(Long postId);
}
