package com.example.productPromotion.config;

import java.time.LocalDateTime;

import com.example.productPromotion.users.Users;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

/**
 *
 * @author angirmaa
 */
@Entity
@Data
@Table(name = "token")

public class Token {
    //this class will help store the token string 
    //store the logged out and token id 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    @Column(name = "token_id") //for all the columns giving a name that same as sql data column name 
    private Long tokenId;

    @Column(name = "token")
    private String token;

    @Column(name = "is_logged_out")
    private boolean loggedOut;
    
    @Column(name ="logout_time")
    private LocalDateTime logoutDateTime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;
    //it will connect to the user id so one user can have many token 
    //Constructor

    public Token() {
       
    }
    public Token(String token, boolean loggedOut, LocalDateTime logoutDateTime, Users users) {
        this.token = token;
        this.loggedOut = loggedOut;
        this.logoutDateTime = logoutDateTime;
        this.users = users;
    }
    
    //Getters and Setters

    public Long getTokenId() {
        return tokenId;
    }

    public void setTokenId(Long tokenId) {
        this.tokenId = tokenId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isLoggedOut() {
        return loggedOut;
    }

    public void setLoggedOut(boolean loggedOut) {
        this.loggedOut = loggedOut;
    }

    public LocalDateTime getLogoutDateTime() {
        return logoutDateTime;
    }

    public void setLogoutDateTime(LocalDateTime logoutDateTime) {
        this.logoutDateTime = logoutDateTime;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }  

}
