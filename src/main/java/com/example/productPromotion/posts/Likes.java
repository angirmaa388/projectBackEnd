/*
* Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
* Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
*/
package com.example.productPromotion.posts;

import com.example.productPromotion.users.Users;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Data;

/**
 *
 * @author angirmaa
 */
//creating the entities for the mysql 
@Entity
@Data
//Giving the table name
@Table(name = "likes")
public class Likes {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    @Column(name = "post_like_id") //for all the columns giving a name that same as sql data column name 
    private Long id;
    
    @Column(name ="liked_time")
    private LocalDateTime likedDateTime;
    
    //one Post can have many likes 
    //so identified this relations
    @ManyToOne
    //here joining the column 
    @JoinColumn(name = "post_id")
    private Posts posts;
    
    //one user can have many likes 
    //so identified this relations
    @ManyToOne
    //here joining the column 
    @JoinColumn(name = "user_id")
    private Users users;
    
    //Constructor

    public Likes() {
        
    }

    public Likes(LocalDateTime likedDateTime, Posts posts, Users users) {
        this.likedDateTime = likedDateTime;
        this.posts = posts;
        this.users = users;
    }
    
    //Getters and setters 

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getLikedDateTime() {
        return likedDateTime;
    }

    public void setLikedDateTime(LocalDateTime likedDateTime) {
        this.likedDateTime = likedDateTime;
    }

    public Posts getPosts() {
        return posts;
    }

    public void setPosts(Posts posts) {
        this.posts = posts;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }
    
    //to string 

    @Override
    public String toString() {
        return "Likes{" + "id=" + id + ", likedDateTime=" + likedDateTime + ", posts=" + posts + ", users=" + users + '}';
    }
    
   
    
}
