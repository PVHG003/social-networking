package vn.pvhg.socialbackend.dto.response;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record PostResponse(
        UUID id,
        String username,
        String profilePicture,
        String content,
        List<PostMediaResponse> postMedias,
        int likeCounts,
        int commentCounts,
        Instant createdAt,
        Instant updatedAt
) {
}
