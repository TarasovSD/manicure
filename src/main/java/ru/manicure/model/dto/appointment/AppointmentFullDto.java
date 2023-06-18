package ru.manicure.model.dto.appointment;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.manicure.annotations.AfterTomorrow;
import ru.manicure.annotations.StartBeforeEnd;
import ru.manicure.model.Status;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@StartBeforeEnd
public class AppointmentFullDto {

    private Long id;
    @NotNull
    private InternalUser client;
    @NotNull
    private InternalUser master;
    @NotNull
    private InternalProcedure procedure;
    @AfterTomorrow
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime start;
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime end;
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime created;
    private Status status;
    private List<InternalComment> comments;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class InternalUser {
        private Long id;
        private String firstName;
        private String lastName;
        private String patronymic;
        private String email;
        private String phone;
        private LocalDate birthday;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class InternalProcedure {
        private Long id;
        private String procedureName;
        private String description;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class InternalComment {
        private Long id;
        private Long userId;
        private String userText;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime created;
    }
}
