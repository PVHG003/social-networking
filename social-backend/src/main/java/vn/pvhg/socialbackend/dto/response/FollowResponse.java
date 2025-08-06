package vn.pvhg.socialbackend.dto.response;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

/**
 * DTO for {@link vn.pvhg.socialbackend.model.friend.Follow}
 */
public record FollowResponse(
        UUID id,
        UUID followingUser,
        UUID followedUser,
        Instant followedAt
) implements Serializable {
}

