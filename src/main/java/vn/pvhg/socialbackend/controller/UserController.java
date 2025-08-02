package vn.pvhg.socialbackend.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.pvhg.socialbackend.dto.response.FollowResponse;
import vn.pvhg.socialbackend.dto.response.PostResponse;
import vn.pvhg.socialbackend.service.FollowService;
import vn.pvhg.socialbackend.service.PostService;

import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final PostService postService;
    private final FollowService followService;

    public UserController(PostService postService, FollowService followService) {
        this.postService = postService;
        this.followService = followService;
    }

    @GetMapping("/{userId}/posts")
    public ResponseEntity<Page<PostResponse>> getAllPostsByUserId(
            @PathVariable UUID userId,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        Page<PostResponse> response = postService.getUserPosts(userId, pageable);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{userId}/followers")
    public ResponseEntity<Page<FollowResponse>> getUserFollowers(
            @PathVariable UUID userId,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("followedAt").descending());
        Page<FollowResponse> response = followService.getUserFollowers(userId, pageable);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{userId}/following")
    public ResponseEntity<Page<FollowResponse>> getUserFollowings(
            @PathVariable UUID userId,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("followedAt").descending());
        Page<FollowResponse> response = followService.getUserFollowings(userId, pageable);
        return ResponseEntity.ok(response);
    }
}
