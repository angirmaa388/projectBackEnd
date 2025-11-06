package com.example.productPromotion.posts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 *
 * @author angirmaa
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostCommentResponse {
    private Long postCommentId;
   private String commentText;
   private String userName;
   private String commentedDateTime;

}
