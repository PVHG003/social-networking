package vn.pvhg.socialbackend.dto.response;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record PostResponse(
        UUID id,
        String userProfileDisplayName,
        String userEmail,
        String content,
        List<PostMediaResponse> postMedias,
//        List<String> postMedias,
        Instant createdAt,
        Instant updatedAt
) {
}
