package com.nishant.ScribbleSpace.model.dto;

import com.nishant.ScribbleSpace.model.PostGenre;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePostInputDTO {
    @NotNull
    private Long id;

    @NotNull
    private String body;

    @NotNull
    private List<PostGenre> postGenres;

    @NotNull
    UserAuthenticatedInformationInputDTO userAuthenticatedInformationInputDTO;
}
