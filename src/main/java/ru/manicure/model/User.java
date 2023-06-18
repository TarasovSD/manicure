package ru.manicure.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @NotBlank
    @Column(name = "first_name", length = 100)
    private String firstName;
    @NotBlank
    @Column(name = "last_name", length = 100)
    private String lastName;
    @Column(name = "patronymic", length = 100)
    private String patronymic;
    @Email
    @NotBlank
    @Column(name = "email", length = 200)
    private String email;
    @NotBlank
    @Column(name = "phone", length = 30)
    private String phone;
    @Past
    @NotNull
    @Column(name = "birthday", nullable = false)
    private LocalDate birthday;
    @NotNull
    @Column(name = "is_master", nullable = false)
    private Boolean isMaster;
}