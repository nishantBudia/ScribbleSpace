package com.nishant.ScribbleSpace.repo;

import com.nishant.ScribbleSpace.model.PostGenre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostGenreRepo extends JpaRepository<PostGenre,String> {
}
