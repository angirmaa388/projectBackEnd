/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.productPromotion.posts;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author angirmaa
 */
@Service
public class LikesService {
     
     private final LikesRepository likesRepository;
     
     @Autowired

    public LikesService(LikesRepository likesRepository) {
        this.likesRepository = likesRepository;
    }

        public List<Likes> getLikes() {
            return likesRepository.findAll();
        }

        public Long likesAmount(Long postId) {
            return likesRepository.countByPosts_PostId(postId);

             }

        public Likes addNewLikes(Likes likes) {
            return likesRepository.save(likes);
            }
    
}
