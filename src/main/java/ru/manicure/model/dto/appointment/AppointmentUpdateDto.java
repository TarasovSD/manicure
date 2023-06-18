package ru.manicure.model.dto.appointment;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ru.manicure.annotations.AfterTomorrow;
import ru.manicure.annotations.StartBeforeEnd;

import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
@StartBeforeEnd
public class AppointmentUpdateDto {

    private Long procedureId;
    @AfterTomorrow
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime start;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime end;
}
