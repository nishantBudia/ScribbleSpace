package com.nishant.ScribbleSpace.service;

import com.nishant.ScribbleSpace.model.ApplicationUser;
import com.nishant.ScribbleSpace.model.Follow;
import com.nishant.ScribbleSpace.repo.FollowRepo;
import com.nishant.ScribbleSpace.repo.projections.FollowerInfo;
import com.nishant.ScribbleSpace.repo.projections.FollowingInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class FollowService {
    @Autowired
    FollowRepo followRepo;

    public Follow createNewFollow(Follow follow) {
        return followRepo.save(follow);
    }

    public List<FollowerInfo> getFollowers(Long applicationUserId) {
        return followRepo.findByFollowing_Id(applicationUserId);
    }

    public List<FollowingInfo> getFollowing(Long id) {
        return followRepo.findByFollower_Id(id);
    }

    @Transactional
    public String deleteFollow(Long followerId, Long followingId) {
        return String.valueOf(followRepo.deleteByFollower_IdAndFollowing_Id(followerId,followingId));
    }
}
