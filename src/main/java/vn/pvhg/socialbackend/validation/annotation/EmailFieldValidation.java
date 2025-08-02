package vn.pvhg.socialbackend.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import vn.pvhg.socialbackend.validation.validator.EmailFieldValidator;

import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailFieldValidator.class)
public @interface EmailFieldValidation {
    String message() default "Invalid email format";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
