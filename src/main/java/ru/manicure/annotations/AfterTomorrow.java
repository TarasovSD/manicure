package ru.manicure.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

/**
 * The annotation does not allow setting the date earlier, than 24 hours from the current one
 */
@Documented
@Constraint(validatedBy = AfterTomorrowProcessor.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AfterTomorrow {
    String message() default "AfterTomorrow annotation";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
