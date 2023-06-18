package ru.manicure.model.dto.procedure;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProcedureFullDto {

    private Long id;
    @NotBlank
    private String procedureName;
    @NotBlank
    private String description;
}