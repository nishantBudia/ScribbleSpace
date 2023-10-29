package com.nishant.ScribbleSpace.repo;

import com.nishant.ScribbleSpace.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepo extends JpaRepository<Comment,Long> {
    boolean existsByCommenter_IdAndId(Long id, Long id1);
    Page<Comment> findByPost_IdOrderByCreationTimeAsc(Long id, Pageable pageable);
}
