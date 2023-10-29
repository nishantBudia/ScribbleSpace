package com.nishant.ScribbleSpace.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteCommentInputDTO {
    @NotNull
    private Long commentId;

    @NotNull
    UserAuthenticatedInformationInputDTO userAuthenticatedInformationInputDTO;
}
