package com.nishant.ScribbleSpace.controller;

import com.nishant.ScribbleSpace.model.Comment;
import com.nishant.ScribbleSpace.model.Follow;
import com.nishant.ScribbleSpace.model.Post;
import com.nishant.ScribbleSpace.model.dto.*;
import com.nishant.ScribbleSpace.repo.projections.FollowerInfo;
import com.nishant.ScribbleSpace.repo.projections.FollowingInfo;
import com.nishant.ScribbleSpace.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @RestController
    @RequestMapping("/user/post")
    public class PostController{
        @PostMapping
        public Post createPost(@RequestBody @Valid CreatePostInputDTO postInputDTO){
            return userService.createPost(postInputDTO);
        }

        @PutMapping()
        public Post editPost(@RequestBody @Valid UpdatePostInputDTO updatePostInputDTO){
            return userService.editPost(updatePostInputDTO);
        }

        @DeleteMapping
        public String deletePost(@RequestBody @Valid DeletePostInputDTO deletePostInputDTO){
            return userService.deletePost(deletePostInputDTO);
        }
    }

    @RestController
    @RequestMapping("/user/comment")
    public class CommentController{
        @PostMapping
        public Comment createCommentForPost(@RequestBody @Valid CreateCommentInputDTO createCommentInputDTO){
            return userService.createCommentForPost(createCommentInputDTO);
        }

        @PostMapping("reply")
        public Comment replyToComment(@RequestBody @Valid ReplyCommentInputDTO replyCommentInputDTO){
            return userService.replyToComment(replyCommentInputDTO);
        }

        @DeleteMapping
        public String deleteComment(@RequestBody @Valid DeleteCommentInputDTO deleteCommentInputDTO){
            return userService.deleteComment(deleteCommentInputDTO);
        }
    }

    @RestController
    @RequestMapping("/user/follow")
    public class FollowController{
        @PostMapping
        public Follow createFollowForUser(@RequestBody @Valid CreateFollowInputDTO createFollowInputDTO){
            return userService.createFollowForUser(createFollowInputDTO);
        }

        @GetMapping("followers")
        public List<FollowerInfo> getFollowers(@Valid UserAuthenticatedInformationInputDTO userAuthenticatedInformationInputDTO){
            return userService.getFollowers(userAuthenticatedInformationInputDTO);
        }

        @GetMapping("following")
        public List<FollowingInfo> getFollowing(@Valid UserAuthenticatedInformationInputDTO userAuthenticatedInformationInputDTO){
            return userService.getFollowing(userAuthenticatedInformationInputDTO);
        }

        @DeleteMapping()
        public String unfollow(@Valid @RequestBody CreateFollowInputDTO createFollowInputDTO){
            return userService.unfollow(createFollowInputDTO);
        }
    }

    @GetMapping("feed/{pageNumber}")
    public List<Post> getUserFeed(
            @Valid UserAuthenticatedInformationInputDTO userAuthenticatedInformationInputDTO,
            @PathVariable Integer pageNumber){
        return userService.getUserFeed(userAuthenticatedInformationInputDTO,pageNumber);
    }
}
