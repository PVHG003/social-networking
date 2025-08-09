package vn.pvhg.socialbackend.dto.request;

import vn.pvhg.socialbackend.model.enums.Gender;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link vn.pvhg.socialbackend.model.UserProfile}
 */
public record ProfileRequest(
        String displayName,
        String bio,
        String location,
        LocalDate birthday,
        Gender gender
) implements Serializable {
}