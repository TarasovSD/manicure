package ru.manicure.annotations;

import lombok.extern.slf4j.Slf4j;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Calendar;
import java.util.Date;

@Slf4j
public class AfterTomorrowProcessor implements ConstraintValidator<AfterTomorrow, Date> {

    public final void initialize(AfterTomorrow constraintAnnotation) {
    }

    public boolean isValid(final Date value, ConstraintValidatorContext context) {
        Calendar c = Calendar.getInstance();
        c.setTime(value);
        c.add(Calendar.DATE, 1);
        return value.after(c.getTime());
    }
}
