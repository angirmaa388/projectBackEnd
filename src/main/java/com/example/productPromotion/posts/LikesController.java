package com.example.productPromotion.posts;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.productPromotion.users.Users;
import com.example.productPromotion.users.UsersRepository;

@CrossOrigin(origins={"http://127.0.0.1:6500", "http://localhost:6500"})
@RestController
@RequestMapping(path = "api/v1/likes")

public class LikesController {

    private final LikesService likesService;

    @Autowired
    public LikesController(LikesService likesService) {
        this.likesService = likesService;
    }
    
    @Autowired 
    private PostsRepository postsRepository;

    @Autowired 
    private UsersRepository usersRepository;

  
    
    @GetMapping
        public List<Likes> getLikes() {
           return likesService.getLikes();
        }

     @PostMapping("/newLikes")
	public Likes newLikes(@RequestParam Long postId, @RequestParam Long userId){
        Posts post = postsRepository.findById(postId).orElse(null);
        Users user = usersRepository.findById(userId).orElse(null);
        Likes likes = new Likes();
        likes.setPosts(post);
        likes.setUsers(user);
        likes.setLikedDateTime(LocalDateTime.now()); 
         return likesService.addNewLikes(likes);
	}

     @PostMapping("/likesCount")
	public Long likesAmount(@RequestBody Long postsId){
                System.out.println("PostId:" + postsId);
         return likesService.likesAmount(postsId);
	}


}
