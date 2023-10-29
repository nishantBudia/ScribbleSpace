package com.nishant.ScribbleSpace.service;

import com.nishant.ScribbleSpace.model.Comment;
import com.nishant.ScribbleSpace.model.Post;
import com.nishant.ScribbleSpace.repo.CommentRepo;
import com.nishant.ScribbleSpace.repo.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuestService {
    @Autowired
    PostRepo postRepo;

    @Autowired
    PostService postService;

    @Autowired
    CommentService commentService;
    @Autowired
    private CommentRepo commentRepo;

    public List<Post> getAllPostsPaginated(Integer number) {
        return postRepo.findAll(PageRequest.of(number,10)).getContent();
    }

    public List<Comment> getAllCommentsForPostPaginated(Long postId, Integer pageNumber) {
        return commentService.getAllCommentsForPostPaginated(postId,pageNumber);
    }

    public List<Post> getPostsByGenrePaginated(String genre,Integer pageNumber) {
        return postService.getPostsByGenrePaginated(genre,pageNumber);
    }
}
