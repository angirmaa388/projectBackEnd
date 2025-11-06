/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.productPromotion.posts;

import com.example.productPromotion.users.Users;
import com.fasterxml.jackson.annotation.JsonBackReference;

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
@Entity
@Data

//Giving the table name
@Table(name = "post_comment")

public class PostComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    @Column(name = "post_comment_id") //for all the columns giving a name that same as sql data column name 
    private Long postCommentId;
    
    @Column(name = "comment_text")
    private String commentText;
    
    @Column(name ="commented_time")
    private LocalDateTime commentedDateTime;
    
    //one Post can have many comments 
    //so identified this relations
    @ManyToOne
    //here joining the column 
    @JoinColumn(name = "post_id")
    @JsonBackReference
    private Posts posts;
    
    //one user can have many comments 
    //so identified this relations
    @ManyToOne
    //here joining the column 
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private Users users;
    
    
    //Constructor

    public PostComment() {
        
    }

    public PostComment(String commentText, LocalDateTime commentedDateTime, Posts posts, Users users) {
        this.commentText = commentText;
        this.commentedDateTime = commentedDateTime;
        this.posts = posts;
        this.users = users;
    }
    
    //Getters and setters 

    public Long getPostCommentId() {
        return postCommentId;
    }

    public void setPostCommentId(Long postCommentId) {
        this.postCommentId = postCommentId;
    }

    
    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public LocalDateTime getCommentedDateTime() {
        return commentedDateTime;
    }

    public void setCommentedDateTime(LocalDateTime commentedDateTime) {
        this.commentedDateTime = commentedDateTime;
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
        return "PostComment{" + "postCommentId=" + postCommentId + ", commentText=" + commentText + ", commentedDateTime=" + commentedDateTime + ", posts=" + posts + ", users=" + users + '}';
    }

    
    
}
