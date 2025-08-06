package vn.pvhg.socialbackend.dto.response;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

public record CommentResponse(
        UUID id,
        String displayName,
        String handleName,
        String profileImage,
        String content,
        Instant createdAt,
        Instant updatedAt
) implements Serializable {
}
