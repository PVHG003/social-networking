package vn.pvhg.socialbackend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.pvhg.socialbackend.dto.request.CommentRequest;
import vn.pvhg.socialbackend.dto.response.CommentResponse;

import java.util.UUID;

public interface CommentService {


    Page<CommentResponse> getPostComments(UUID postId, Pageable pageable);

    CommentResponse postComment(UUID postId, CommentRequest request);

    CommentResponse getCommentById(UUID commentId);

    CommentResponse updateComment(UUID commentId, CommentRequest request);

    void deleteComment(UUID commentId);
}
