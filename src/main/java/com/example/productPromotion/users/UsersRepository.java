/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.productPromotion.users;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author angirmaa
 */
@Repository
public interface UsersRepository 
        extends JpaRepository <Users, Long> {
    
   @Query("SELECT u FROM Users u WHERE u.email = ?1")
   Optional<Users> findUsersByEmail(String email);
   
   Optional<Users> findByEmail(String email);
    
}
