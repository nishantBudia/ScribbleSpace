package com.nishant.ScribbleSpace.repo;

import com.nishant.ScribbleSpace.model.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface PostRepo extends JpaRepository<Post,Long> {
    List<Post> findByOwner_IdIn(Collection<Long> ids, Pageable pageable);
    List<Post> findByPostGenres_GenreOrderByCreationTimeStampAsc(String genre, Pageable pageable);
    boolean existsByOwner_IdAndId(Long id, Long id1);
}
