package com.nishant.ScribbleSpace.repo.projections;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.nishant.ScribbleSpace.model.ApplicationUser;

/**
 * Projection for {@link com.nishant.ScribbleSpace.model.Follow}
 */
public interface FollowerInfo {
    @JsonIncludeProperties({"id","username"})
    ApplicationUser getFollower();
}