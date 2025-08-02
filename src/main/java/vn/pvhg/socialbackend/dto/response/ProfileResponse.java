package vn.pvhg.socialbackend.dto.response;

import vn.pvhg.socialbackend.model.enums.Gender;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

/**
 * DTO for {@link vn.pvhg.socialbackend.model.UserProfile}
 */
public record ProfileResponse(
        UUID id,
        String email,
        String displayName,
        String bio,
        String profileImage,
        String location,
        String websiteUrl,
        LocalDate birthdate,
        Gender gender,
        Instant createdAt,
        Instant updatedAt
) implements Serializable {
}