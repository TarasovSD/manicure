package ru.manicure.model.dto.appointment;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ru.manicure.annotations.AfterTomorrow;
import ru.manicure.annotations.StartBeforeEnd;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
@StartBeforeEnd
public class AppointmentDto {

    private Long id;
    @NotNull
    private Long clientId;
    @NotNull
    private Long masterId;
    @NotNull
    private Long procedureId;
    @AfterTomorrow
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime start;
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime end;
    private String clientComment;
}
