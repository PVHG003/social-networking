package vn.pvhg.socialbackend.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import vn.pvhg.socialbackend.dto.request.RegisterRequest;
import vn.pvhg.socialbackend.validation.annotation.RegisterRequestFormValidation;

import java.util.regex.Pattern;

public class RegisterRequestFormValidator implements ConstraintValidator<RegisterRequestFormValidation, RegisterRequest> {

    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{8,}$");

    @Override
    public void initialize(RegisterRequestFormValidation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(RegisterRequest value, ConstraintValidatorContext context) {
        boolean valid = true;

        if (!PASSWORD_PATTERN.matcher(value.password()).matches()) {
            context.buildConstraintViolationWithTemplate("Invalid password.")
                    .addPropertyNode("password")
                    .addConstraintViolation();
            valid = false;
        }

        if (!value.confirmPassword().equals(value.password())) {
            context.buildConstraintViolationWithTemplate("Passwords don't match.")
                    .addPropertyNode("confirmPassword")
                    .addConstraintViolation();
            valid = false;
        }

        return valid;
    }
}
