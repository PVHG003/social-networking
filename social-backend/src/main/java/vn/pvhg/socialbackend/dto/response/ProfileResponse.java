package vn.pvhg.socialbackend.dto.response;

import vn.pvhg.socialbackend.model.enums.Gender;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

/**
 * DTO for {@link vn.pvhg.socialbackend.model.UserProfile}
 */
public record ProfileResponse(
        UUID id,
        String email,
        String handleName,
        String displayName,
        String bio,
        String profileImage,
        String location,
        String websiteUrl,
        LocalDate birthday,
        Gender gender
) implements Serializable {
}