package vn.pvhg.socialbackend.dto.response;

import vn.pvhg.socialbackend.model.enums.MediaType;

import java.io.Serializable;

/**
 * DTO for {@link vn.pvhg.socialbackend.model.post.PostMedia}
 */
public record PostMediaResponse(
//        Long id,
        String originalFilename,
        String storagePath,
//        long fileSize,
        String mimeType,
//        int size,
//        Integer height,
//        Integer width,
//        Integer duration,
        int position,
        MediaType mediaType
//        boolean deleted,
//        Instant createdAt,
//        Instant deletedAt
) implements Serializable {
}