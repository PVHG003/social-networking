package vn.pvhg.socialbackend.dto.response;

import vn.pvhg.socialbackend.model.enums.Gender;

import java.io.Serializable;
import java.util.UUID;

public record UserProfileResponse(
        UUID id,
        String handleName,
        String displayName,
        String profileImage,
        String bio,
        Gender gender
) implements Serializable {
}
