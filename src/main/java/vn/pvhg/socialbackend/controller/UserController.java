package vn.pvhg.socialbackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.pvhg.socialbackend.dto.response.FollowResponse;
import vn.pvhg.socialbackend.dto.response.PostResponse;
import vn.pvhg.socialbackend.response.ApiPagedResponse;
import vn.pvhg.socialbackend.service.FollowService;
import vn.pvhg.socialbackend.service.PostService;

import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final PostService postService;
    private final FollowService followService;

    @GetMapping("/{userId}/posts")
    public ResponseEntity<Object> getAllPostsByUserId(
            @PathVariable UUID userId,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        Page<PostResponse> postResponsePage = postService.getUserPosts(userId, pageable);
        ApiPagedResponse<PostResponse> response = new ApiPagedResponse<>(
                HttpStatus.OK,
                true,
                "",
                postResponsePage.getContent(),
                postResponsePage.getNumber(),
                postResponsePage.getNumberOfElements(),
                postResponsePage.getTotalElements(),
                postResponsePage.getTotalPages());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{userId}/followers")
    public ResponseEntity<Object> getUserFollowers(
            @PathVariable UUID userId,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("followedAt").descending());
        Page<FollowResponse> followResponsePage = followService.getUserFollowers(userId, pageable);
        ApiPagedResponse<FollowResponse> response = new ApiPagedResponse<>(
                HttpStatus.OK,
                true,
                "",
                followResponsePage.getContent(),
                followResponsePage.getNumber(),
                followResponsePage.getNumberOfElements(),
                followResponsePage.getTotalElements(),
                followResponsePage.getTotalPages());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{userId}/following")
    public ResponseEntity<Object> getUserFollowings(
            @PathVariable UUID userId,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("followedAt").descending());
        Page<FollowResponse> followResponsePage = followService.getUserFollowings(userId, pageable);
        ApiPagedResponse<FollowResponse> response = new ApiPagedResponse<>(
                HttpStatus.OK,
                true,
                "",
                followResponsePage.getContent(),
                followResponsePage.getNumber(),
                followResponsePage.getNumberOfElements(),
                followResponsePage.getTotalElements(),
                followResponsePage.getTotalPages());
        return ResponseEntity.ok(response);
    }
}
