package com.nishant.ScribbleSpace.model.PK;


import com.nishant.ScribbleSpace.model.ApplicationUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FollowPK implements Serializable {
    private ApplicationUser follower;

    private ApplicationUser following;
}
