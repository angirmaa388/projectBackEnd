/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.productPromotion.posts;


import java.util.List;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author angirmaa
 */
public interface PostsRepository 
        extends JpaRepository <Posts, Long>  {

       @Query("SELECT p FROM Posts p JOIN p.users u " +
       " WHERE LOWER(p.postText) LIKE LOWER(CONCAT('%', :keyword, '%')) " + 
       " OR LOWER(u.userName) LIKE LOWER(CONCAT('%', :keyword, '%'))")
        List<Posts> searchPosts(@Param("keyword")String keyword);
        //this query will help to find the key word post from post database 
}
