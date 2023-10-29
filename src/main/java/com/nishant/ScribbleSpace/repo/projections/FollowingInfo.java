package com.nishant.ScribbleSpace.repo.projections;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.nishant.ScribbleSpace.model.ApplicationUser;

/**
 * Projection for {@link com.nishant.ScribbleSpace.model.Follow}
 */
public interface FollowingInfo {
    @JsonIncludeProperties({"id","username"})
    ApplicationUser getFollowing();
}