package vn.pvhg.socialbackend.dto.request;

import jakarta.validation.constraints.NotBlank;
import vn.pvhg.socialbackend.validation.annotation.EmailFieldValidation;
import vn.pvhg.socialbackend.validation.annotation.RegisterRequestFormValidation;

@RegisterRequestFormValidation
public record RegisterRequest(
        @NotBlank(message = "Email is required")
        @EmailFieldValidation
        String email,
        @NotBlank(message = "Password is required")
        String password,
        @NotBlank(message = "Password is required")
        String confirmPassword
) {
}
