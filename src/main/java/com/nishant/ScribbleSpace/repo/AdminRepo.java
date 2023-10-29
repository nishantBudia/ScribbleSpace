package com.nishant.ScribbleSpace.repo;

import com.nishant.ScribbleSpace.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepo extends JpaRepository<Admin,Short> {
}
