package ru.manicure.annotations;

import lombok.extern.slf4j.Slf4j;
import ru.manicure.model.dto.appointment.AppointmentDto;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Slf4j
public class StartBeforeEndProcessor implements ConstraintValidator<StartBeforeEnd, AppointmentDto> {

    public void initialize(StartBeforeEnd constraintAnnotation) {
    }

    public boolean isValid(AppointmentDto appointmentDto, ConstraintValidatorContext constraintValidatorContext) {
        log.info(appointmentDto.toString());
        return appointmentDto.getStart().isBefore(appointmentDto.getEnd());
    }
}
