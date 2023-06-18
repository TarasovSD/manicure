package ru.manicure.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = StartBeforeEndProcessor.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface StartBeforeEnd {
    String message() default "StartBeforeEnd annotation";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
