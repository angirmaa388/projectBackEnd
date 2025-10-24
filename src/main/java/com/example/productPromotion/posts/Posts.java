/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.productPromotion.posts;

import com.example.productPromotion.users.Users;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Table(name = "posts")

public class Posts {
   //Declaring variables 
    // Giving the column name 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    @Column(name = "post_id") //for all the columns giving a name that same as sql data column name 
    private Long postId;
    
    @Column(name = "post_text")
    private String postText;
    
    @Column(name = "file_path")
    private String filePath;
    
    
    @Column(name = "file_type")
    private String fileType; 
    
    @Column(name ="posted_time")
    private LocalDateTime postedDateTime;
    
    //one customer can have many posts 
    //so identified this relations
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne
    //here joining the column 
    @JoinColumn(name = "user_id")
    private Users users;
    
    //Contructors 

    public Posts() {
        
    }

    public Posts( String postText, String filePath, String fileType, LocalDateTime postedDateTime, Users users) {
        
        this.postText = postText;
        this.filePath = filePath;
        this.fileType = fileType;
        this.postedDateTime = postedDateTime;
        this.users = users;
    }

   
    
    public Long getPostId() {
        return postId;
    }

    //Getters and setters
    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public LocalDateTime getPostedDateTime() {
        return postedDateTime;
    }

    public void setPostedDateTime(LocalDateTime postedDateTime) {
        this.postedDateTime = postedDateTime;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    //To string 

    @Override
    public String toString() {
        return "Posts{" + "postId=" + postId + ", postText=" + postText + ", filePath=" + filePath + ", fileType=" + fileType + ", postedDateTime=" + postedDateTime + ", users=" + users + '}';
    }

    
    
}
