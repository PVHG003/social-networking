package vn.pvhg.socialbackend.dto.request;

import java.io.Serializable;

public record CommentRequest(
        String content
) implements Serializable {
}
