/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.productPromotion.users;
import com.example.productPromotion.posts.Posts;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.List;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author angirmaa
 */

@Entity
@Data
 //Giving the table name
@Table (name = "users")


public class Users implements UserDetails{
    //Declaring variables 
    // Giving the column name 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    @Column(name = "user_id") //for all the columns giving a name that same as sql data column name 
    private Long userId;
    
    @Column(name = "user_name")
    private String userName;
    
    @Email(message = "Invalid email")
    @NotBlank(message = "email required") 
    @Column(name = "email")
    private String email;
    
    
    @Column(name = "user_password")
    private String password;
    
    @Column(name = "user_status")
    private String status;
    
    
    @OneToMany(mappedBy="users")
    private List<Posts>posts; 
    
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Posts> getPosts() {
        return posts;
    }

    public void setPosts(List<Posts> posts) {
        this.posts = posts;
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
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
//To string 
    @Enumerated(EnumType.STRING)
    //it will take the string value of the enum 
    private Role role;

    @Override
    public String toString() {
        return "Users{" + "userId=" + userId + ", userName=" + userName + ", email=" + email + ", password=" + password + ", status=" + status + ", posts=" + posts + '}';
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name())); 
        }

    @Override
    public String getUsername() {
        return email; 
        }
     
   

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getPassword() {
        return password;

       }
    
    
}
