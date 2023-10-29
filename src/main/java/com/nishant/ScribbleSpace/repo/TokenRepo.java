package com.nishant.ScribbleSpace.repo;

import com.nishant.ScribbleSpace.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
public interface TokenRepo extends JpaRepository<Token,String> {
}
