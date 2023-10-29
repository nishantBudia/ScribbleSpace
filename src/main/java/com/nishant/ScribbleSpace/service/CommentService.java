package com.nishant.ScribbleSpace.service;

import com.nishant.ScribbleSpace.model.Comment;
import com.nishant.ScribbleSpace.repo.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    CommentRepo commentRepo;

    public List<Comment> getAllCommentsForPostPaginated(Long postId, Integer pageNumber) {
        return commentRepo.findByPost_IdOrderByCreationTimeAsc(postId, PageRequest.of(pageNumber,10)).getContent();
    }

    public Comment createCommentForPost(Comment comment) {
        return commentRepo.save(comment);
    }

    public Comment getCommentById(Long commentId) {
        return commentRepo.findById(commentId).orElseThrow();
    }

    @Transactional
    public boolean isMyComment(Long userId, Long commentId) {
        return commentRepo.existsByCommenter_IdAndId(userId,commentId);
    }

    public String deleteComment(Long commentId) {
        commentRepo.deleteById(commentId);
        return "Success";
    }
}
