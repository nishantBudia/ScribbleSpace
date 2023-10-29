package com.nishant.ScribbleSpace.model.dto;

import com.nishant.ScribbleSpace.model.Post;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCommentInputDTO {

    @NotNull
    private String body;

    @NotNull
    private Long postId;

    @NotNull
    UserAuthenticatedInformationInputDTO userAuthenticatedInformationInputDTO;
}
