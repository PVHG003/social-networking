package vn.pvhg.socialbackend.dto.response;

import java.time.Instant;

public record LikeResponse(
        String displayName,
        String handleName,
        Instant likedAt
) {
}
