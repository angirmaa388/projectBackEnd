/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.productPromotion.users;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.Data;
/**
 *
 * @author angirmaa
 */
@Entity
@Data
 //Giving the table name
@Table (name = "users")

public class Users {
    //Declaring variables 
    // Giving the column name 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    @Column(name = "user_id") //for all the columns giving a name that same as sql data column name 
    private Long id;
    
    @Column(name = "user_name")
    private String userName;
    
    @Email(message = "Invalid email")
    @Column(name = "email")
    private String email;
    
    
    @Column(name = "user_password")
    private String password;
    
    @Column(name = "user_status")
    private String status;
    
    //Constructor

    public Users() 
    {
        
    }

    public Users(String userName, 
                         String email, 
                         String password, 
                         String status) 
    {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.status = status;
    }
    
    
    //getters and setters 

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    
}
