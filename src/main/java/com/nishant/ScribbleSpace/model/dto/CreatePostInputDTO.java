package com.nishant.ScribbleSpace.model.dto;

import com.nishant.ScribbleSpace.model.PostGenre;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePostInputDTO {
    @NotNull
    private String title;

    @NotNull
    private String body;

    private List<PostGenre> postGenres;

    @NotNull
    UserAuthenticatedInformationInputDTO userAuthenticatedInformationInputDTO;
}
