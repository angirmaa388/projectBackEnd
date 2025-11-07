package com.example.productPromotion.config;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.productPromotion.users.Users;

/**
 *
 * @author angirmaa
 */
public interface TokenRepository extends JpaRepository<Token, Long> {
    @Query("SELECT t FROM Token t WHERE t.users.userId = :userId and t.loggedOut =false")
    List<Token> findAllTokenByUser(Long userId);
    Optional<Token> findByToken(String token);

}
