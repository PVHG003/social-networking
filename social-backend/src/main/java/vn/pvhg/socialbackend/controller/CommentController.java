package vn.pvhg.socialbackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import vn.pvhg.socialbackend.dto.request.CommentRequest;
import vn.pvhg.socialbackend.dto.response.CommentResponse;
import vn.pvhg.socialbackend.response.ApiPagedResponse;
import vn.pvhg.socialbackend.response.ApiResponse;
import vn.pvhg.socialbackend.service.CommentService;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<ApiPagedResponse<CommentResponse>> getPostComments(
            @PathVariable UUID postId,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<CommentResponse> comments = commentService.getPostComments(postId, pageable);
        ApiPagedResponse<CommentResponse> response = new ApiPagedResponse<>(
                HttpStatus.OK,
                true,
                "Comments of post " + postId.toString() + " found",
                comments.getContent(),
                comments.getNumber(),
                comments.getNumberOfElements(),
                comments.getTotalElements(),
                comments.getTotalPages());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<ApiResponse<CommentResponse>> postComment(
            UriComponentsBuilder uriComponentsBuilder,
            @PathVariable UUID postId,
            @RequestBody CommentRequest request) {
        CommentResponse comment = commentService.postComment(postId, request);
        ApiResponse<CommentResponse> response = new ApiResponse<>(HttpStatus.CREATED, true, "Comment posted", comment);
        URI uri = uriComponentsBuilder.buildAndExpand("/api/comments/{id}").expand(comment.id()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping("/comments/{commentId}")
    public ResponseEntity<ApiResponse<CommentResponse>> getCommentById(@PathVariable UUID commentId) {
        CommentResponse comment = commentService.getCommentById(commentId);
        ApiResponse<CommentResponse> response = new ApiResponse<>(HttpStatus.CREATED, true, "Comment posted", comment);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/comments/{commentId}")
    public ResponseEntity<ApiResponse<CommentResponse>> updateComment(@PathVariable UUID commentId, @RequestBody CommentRequest request) {
        CommentResponse comment = commentService.updateComment(commentId, request);
        ApiResponse<CommentResponse> response = new ApiResponse<>(HttpStatus.CREATED, true, "Comment posted", comment);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<ApiResponse<Void>> deleteComment(@PathVariable UUID commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.noContent().build();
    }
}
