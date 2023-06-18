package ru.manicure.model.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Past;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateDto {
    private String firstName;
    private String lastName;
    private String patronymic;
    @Email
    private String email;
    private String phone;
    @Past
    private LocalDate birthday;
    private Boolean isMaster;
}
