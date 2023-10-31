package com.nishant.ScribbleSpace.controller;

import com.nishant.ScribbleSpace.model.Comment;
import com.nishant.ScribbleSpace.model.Post;
import com.nishant.ScribbleSpace.model.dto.UserAuthenticationInputDTO;
import com.nishant.ScribbleSpace.model.dto.UserRegistrationInputDTO;
import com.nishant.ScribbleSpace.service.AuthenticationService;
import com.nishant.ScribbleSpace.service.GuestService;
import com.nishant.ScribbleSpace.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GuestController {
    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    GuestService guestService;

    @RestController
    @RequestMapping("guest/registration")
    public class RegistrationController{
        @PostMapping("user")
        public String registerUser(@RequestBody @Valid UserRegistrationInputDTO userRegistrationInputDTO){
            return authenticationService.registerUser(userRegistrationInputDTO.getEmail(), userRegistrationInputDTO.getPassword());
        }
    }

    @RestController
    @RequestMapping("guest/verification")
    public class VerificationController{
        @GetMapping("user")
        public String verifyUserEmail(@RequestParam @Email String email, @RequestParam String token){
            return authenticationService.verifyUserEmail(email,token);
        }
    }

    @RestController
    @RequestMapping("guest/authentication")
    public class AuthenticationController{
        @PostMapping("user")
        public String authenticateUser(@RequestBody @Valid UserAuthenticationInputDTO userAuthenticationInputDTO){
            return authenticationService.authenticateUser(userAuthenticationInputDTO.getEmail(), userAuthenticationInputDTO.getPassword());
        }
    }

    @RestController
    @RequestMapping("guest/post")
    public class PostController{
        @GetMapping("page/{number}")
        public List<Post> getAllPostsPaginated(@PathVariable Integer number){
            return guestService.getAllPostsPaginated(number);
        }

        @GetMapping("{genre}")
        public List<Post> getPostsByGenrePaginated(@PathVariable String genre, @RequestParam Integer pageNumber){
            return guestService.getPostsByGenrePaginated(genre,pageNumber);
        }
    }

    @RestController
    @RequestMapping("guest/comment")
    public class CommentController{
        @GetMapping("post/{id}")
        public List<Comment> getAllCommentsPaginated(@PathVariable Long id, @RequestParam Integer pageNumber){
            return guestService.getAllCommentsForPostPaginated(id,pageNumber);
        }
    }
}
