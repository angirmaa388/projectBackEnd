package com.example.productPromotion.users;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author angirmaa
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsersResponse {
    private Long userId;
    private String userName;
    private String email;
    private String status;
    

}
