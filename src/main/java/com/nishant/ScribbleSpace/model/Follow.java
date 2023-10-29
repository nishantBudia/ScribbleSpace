package com.nishant.ScribbleSpace.model;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.nishant.ScribbleSpace.model.PK.FollowPK;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "follows")
@IdClass(FollowPK.class)
public class Follow {

    @JsonIncludeProperties({"id"})
    @NotNull
    @Id
    @ManyToOne
    private ApplicationUser follower;

    @JsonIncludeProperties({"id"})
    @NotNull
    @Id
    @ManyToOne
    private ApplicationUser following;
}
