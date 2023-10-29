package com.nishant.ScribbleSpace.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeletePostInputDTO {
    @NotNull
    private Long id;

    @NotNull
    UserAuthenticatedInformationInputDTO userAuthenticatedInformationInputDTO;
}
