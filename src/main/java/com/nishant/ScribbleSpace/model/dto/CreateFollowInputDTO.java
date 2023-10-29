package com.nishant.ScribbleSpace.model.dto;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.nishant.ScribbleSpace.model.ApplicationUser;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateFollowInputDTO {

    @NotNull
    private Long followingId;

    @NotNull
    UserAuthenticatedInformationInputDTO userAuthenticatedInformationInputDTO;
}
