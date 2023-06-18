package ru.manicure.model.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserFullDto {

    private Long id;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    private String patronymic;
    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String phone;
    @NotNull
    @Past
    private LocalDate birthday;
    @NotNull
    private Boolean isMaster;
}
