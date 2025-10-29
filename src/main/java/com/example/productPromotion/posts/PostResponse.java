package com.example.productPromotion.posts;

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
   private String postedDateTime;
   private String userName;
}
