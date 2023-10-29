package com.nishant.ScribbleSpace.repo;

import com.nishant.ScribbleSpace.model.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<ApplicationUser,Long> {
    ApplicationUser findByUsername(String username);
}
