package com.example.productPromotion.config;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.HexFormat;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoder;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
    private static final String SECRET_KEY = "02140751d096413d0f2fa8535f3a73ca55c842f8d2a56dc8f250340d3b14668d";
    public String extractUserName(String token){
        return extractClaim(token, Claims:: getSubject);
        //it will take the user name 
    }
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims =extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    //this class will generate token from user detials 
    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(), userDetails);
    }
    //this class will generate the token 
    public String generateToken(
        Map<String, Object> extractClaims,
        UserDetails userDetails
        //it will contain the extract claims 
    ){
        return Jwts
        .builder()
        .setClaims(extractClaims)
        .setSubject(userDetails.getUsername())
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis()+ 1000 * 60 * 60 * 24))
        //token will expire 24 hours 
        .signWith(getSignInKey(), SignatureAlgorithm.HS256)
        .compact();

    }
    //it will validate the token ]
    public boolean isTokenValid(String token, UserDetails userDetails ){
        final String userName = extractUserName(token);
        System.out.println("Decoded email from token: " + userName);
        return (userName.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }
    private boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token){
        return Jwts
        .parserBuilder()
        .setSigningKey(getSignInKey())
        //when it creates jwt or check it
        //it creates a secret key 
        // for this it will adress the token after it will use it to check it 
        .build().parseClaimsJws(token)
        .getBody();
    }
    private Key getSignInKey() {
        byte[] keyBytes = HexFormat.of().parseHex(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
       }

}
