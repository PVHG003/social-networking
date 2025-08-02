package vn.pvhg.socialbackend.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import vn.pvhg.socialbackend.validation.validator.RegisterRequestFormValidator;

import java.lang.annotation.*;

@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RegisterRequestFormValidator.class)
public @interface RegisterRequestFormValidation {
    String message() default "Invalid registration form";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
