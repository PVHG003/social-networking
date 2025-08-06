package vn.pvhg.socialbackend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.pvhg.socialbackend.dto.response.LikeResponse;

import java.util.UUID;

public interface LikeService {
    void likePost(UUID postId);

    void unlikePost(UUID postId);

    Page<LikeResponse> getPostLikes(UUID postId, Pageable pageable);
}
