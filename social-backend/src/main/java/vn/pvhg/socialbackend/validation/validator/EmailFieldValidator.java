package vn.pvhg.socialbackend.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import vn.pvhg.socialbackend.validation.annotation.EmailFieldValidation;

import java.util.regex.Pattern;

public class EmailFieldValidator implements ConstraintValidator<EmailFieldValidation, String> {
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[\\w.-]+@[\\w.-]+\\.\\w{2,}$");

    @Override
    public void initialize(EmailFieldValidation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();

        if (!EMAIL_PATTERN.matcher(value).matches()) {
            context.buildConstraintViolationWithTemplate("Invalid email address.")
                    .addConstraintViolation();
            return false;
        }

        return true;
    }
}
