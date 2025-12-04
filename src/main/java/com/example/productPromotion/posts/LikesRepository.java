/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.productPromotion.posts;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author angirmaa
 */
public interface LikesRepository 
        extends JpaRepository <Likes, Long>  {
                //this class will allow to server connect to data base 

        Long countByPosts_PostId(Long postId);
    
}
