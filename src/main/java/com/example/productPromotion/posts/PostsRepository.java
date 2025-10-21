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
public interface PostsRepository 
        extends JpaRepository <Posts, Long>  {
    
}
