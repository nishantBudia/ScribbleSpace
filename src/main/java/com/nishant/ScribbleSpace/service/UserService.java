package com.nishant.ScribbleSpace.service;

import com.nishant.ScribbleSpace.model.ApplicationUser;
import com.nishant.ScribbleSpace.model.Comment;
import com.nishant.ScribbleSpace.model.Follow;
import com.nishant.ScribbleSpace.model.Post;
import com.nishant.ScribbleSpace.model.dto.*;
import com.nishant.ScribbleSpace.repo.UserRepo;
import com.nishant.ScribbleSpace.repo.projections.FollowerInfo;
import com.nishant.ScribbleSpace.repo.projections.FollowingInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    PostService postService;

    @Autowired
    CommentService commentService;

    @Autowired
    FollowService followService;

    @Autowired
    UserRepo userRepo;

    public Comment createCommentForPost(CreateCommentInputDTO createCommentInputDTO) {
        if(authenticationService.verifyAuthenticatedInformationUser(createCommentInputDTO.getUserAuthenticatedInformationInputDTO())){
            return commentService.createCommentForPost(
                    new Comment(
                            createCommentInputDTO.getBody(),
                            userRepo.findById(createCommentInputDTO.getUserAuthenticatedInformationInputDTO().getId()).orElseThrow(),
                            postService.getPostById(createCommentInputDTO.getPostId()),
                            null
                            )
            );
        }
        else{
            return null;
        }
    }

    public Post createPost(CreatePostInputDTO postInputDTO) {
        if(authenticationService.verifyAuthenticatedInformationUser(postInputDTO.getUserAuthenticatedInformationInputDTO())){
            return postService.createPost(
                    new Post(
                            postInputDTO.getTitle(),
                            postInputDTO.getBody(),
                            postInputDTO.getPostGenres(),
                            userRepo.findById(
                                    postInputDTO
                                            .getUserAuthenticatedInformationInputDTO()
                                            .getId()).orElseThrow()));
        }
        else{
            return null;
        }
    }

    public Post editPost(UpdatePostInputDTO updatePostInputDTO) {
        if(authenticationService.verifyAuthenticatedInformationUser(updatePostInputDTO.getUserAuthenticatedInformationInputDTO())){

            Post post = postService.getPostById(updatePostInputDTO.getId());

            if(!post.getOwner().getId().equals(updatePostInputDTO.getUserAuthenticatedInformationInputDTO().getId())){
                return null;
            }

            post.setBody(updatePostInputDTO.getBody());
            post.setPostGenres(updatePostInputDTO.getPostGenres());
            return postService.updatePost(post);
        }
        else{
            return null;
        }
    }

    public Comment replyToComment(ReplyCommentInputDTO replyCommentInputDTO) {
        if(authenticationService.verifyAuthenticatedInformationUser(replyCommentInputDTO.getUserAuthenticatedInformationInputDTO())){

            Comment replyTo = commentService.getCommentById(replyCommentInputDTO.getCommentId());

            return commentService.createCommentForPost(
                    new Comment(
                            replyCommentInputDTO.getBody(),
                            userRepo.findById(replyCommentInputDTO.getUserAuthenticatedInformationInputDTO().getId()).orElseThrow(),
                            postService.getPostById(replyTo.getId()),
                            replyTo
                    )
            );
        }
        else{
            return null;
        }
    }

    public Follow createFollowForUser(CreateFollowInputDTO createFollowInputDTO) {
        if(authenticationService.verifyAuthenticatedInformationUser(createFollowInputDTO.getUserAuthenticatedInformationInputDTO())){
            return followService.createNewFollow(
                    new Follow(
                            userRepo.findById(
                                    createFollowInputDTO
                                            .getUserAuthenticatedInformationInputDTO()
                                            .getId())
                                    .orElseThrow(),
                            userRepo.findById(createFollowInputDTO.getFollowingId()).orElseThrow()
                    )
            );
        }
        else{
            return null;
        }
    }

    public List<FollowerInfo> getFollowers(UserAuthenticatedInformationInputDTO userAuthenticatedInformationInputDTO) {
        if(authenticationService.verifyAuthenticatedInformationUser(userAuthenticatedInformationInputDTO)){
            return followService.getFollowers(
                    userAuthenticatedInformationInputDTO.getId());
        }
        else{
            return null;
        }
    }

    public List<FollowingInfo> getFollowing(UserAuthenticatedInformationInputDTO userAuthenticatedInformationInputDTO) {
        if(authenticationService.verifyAuthenticatedInformationUser(userAuthenticatedInformationInputDTO)){
            return followService.getFollowing(
                    userAuthenticatedInformationInputDTO.getId());
        }
        else{
            return null;
        }
    }

    public String unfollow(CreateFollowInputDTO createFollowInputDTO) {
        if(authenticationService.verifyAuthenticatedInformationUser(createFollowInputDTO.getUserAuthenticatedInformationInputDTO())){
            return followService.deleteFollow(
                    createFollowInputDTO.getUserAuthenticatedInformationInputDTO().getId(),
                    createFollowInputDTO.getFollowingId()
            );
        }
        else{
            return null;
        }
    }

    public String deletePost(DeletePostInputDTO deletePostInputDTO) {
        if(authenticationService.verifyAuthenticatedInformationUser(deletePostInputDTO.getUserAuthenticatedInformationInputDTO())){
            if(!postService.isMyPost(deletePostInputDTO.getUserAuthenticatedInformationInputDTO().getId(),deletePostInputDTO.getId())){
                return null;
            }
            return postService.deletePost(deletePostInputDTO.getId());
        }
        else{
            return null;
        }
    }

    public String deleteComment(DeleteCommentInputDTO deleteCommentInputDTO) {
        if(authenticationService.verifyAuthenticatedInformationUser(deleteCommentInputDTO.getUserAuthenticatedInformationInputDTO())){
            if(!commentService.isMyComment(
                    deleteCommentInputDTO.getUserAuthenticatedInformationInputDTO().getId(),
                    deleteCommentInputDTO.getCommentId())){
                return "Not your comment";
            }

            return commentService.deleteComment(deleteCommentInputDTO.getCommentId());
        }
        else{
            return null;
        }
    }

    public List<Post> getUserFeed(UserAuthenticatedInformationInputDTO userAuthenticatedInformationInputDTO, Integer pageNumber) {
        if(authenticationService.verifyAuthenticatedInformationUser(userAuthenticatedInformationInputDTO)){
            List<Long> followers = getFollowers(userAuthenticatedInformationInputDTO)
                    .stream()
                    .map((followerInfo)->{
                        return followerInfo.getFollower().getId();
                    }).toList();

            return postService.getPostsForUsersPaginated(followers,pageNumber);
        }
        else{
            return null;
        }
    }
}
