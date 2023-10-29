package com.nishant.ScribbleSpace.model.dto;

import com.nishant.ScribbleSpace.model.Comment;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReplyCommentInputDTO {
    @NotNull
    private String body;

    @NotNull
    private Long commentId;

    @NotNull
    UserAuthenticatedInformationInputDTO userAuthenticatedInformationInputDTO;
}
