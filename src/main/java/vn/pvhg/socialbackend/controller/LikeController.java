package vn.pvhg.socialbackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.pvhg.socialbackend.dto.response.LikeResponse;
import vn.pvhg.socialbackend.response.ApiPagedResponse;
import vn.pvhg.socialbackend.response.ApiResponse;
import vn.pvhg.socialbackend.service.LikeService;

import java.util.UUID;

@RestController
@RequestMapping("/api/posts/{postId}/likes")
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService;

    @GetMapping
    public ResponseEntity<ApiPagedResponse<LikeResponse>> getLikes(@PathVariable UUID postId) {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("createdAt").descending());
        Page<LikeResponse> likes = likeService.getPostLikes(postId, pageable);
        ApiPagedResponse<LikeResponse> response = new ApiPagedResponse<>(
                HttpStatus.OK,
                true,
                "Posts found",
                likes.getContent(),
                likes.getPageable().getPageNumber(),
                likes.getPageable().getPageSize(),
                likes.getTotalElements(),
                likes.getTotalPages());
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> likePost(@PathVariable UUID postId) {
        likeService.likePost(postId);
        ApiResponse<Void> response = new ApiResponse<>(HttpStatus.OK, true, "Post liked", null);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse<Void>> unlikePost(@PathVariable UUID postId) {
        likeService.unlikePost(postId);
        ApiResponse<Void> response = new ApiResponse<>(HttpStatus.OK, true, "Post unliked", null);
        return ResponseEntity.ok(response);
    }
}
