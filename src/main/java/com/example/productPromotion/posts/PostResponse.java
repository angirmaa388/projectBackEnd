package com.example.productPromotion.posts;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {
   private Long postId;
   private String postText;
   private String filePath;
   private String fileType;
   private String userName;
   private String postedDateTime;

    
}
