package com.nishant.ScribbleSpace.repo;

import com.nishant.ScribbleSpace.model.ApplicationUser;
import com.nishant.ScribbleSpace.model.Follow;
import com.nishant.ScribbleSpace.model.PK.FollowPK;
import com.nishant.ScribbleSpace.repo.projections.FollowerInfo;
import com.nishant.ScribbleSpace.repo.projections.FollowingInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowRepo extends JpaRepository<Follow, FollowPK> {
    List<FollowingInfo> findByFollower_Id(Long id);
    long deleteByFollower_IdAndFollowing_Id(Long id, Long id1);
    List<FollowerInfo> findByFollowing_Id(Long id);
}
