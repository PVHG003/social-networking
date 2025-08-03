package vn.pvhg.socialbackend.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.pvhg.socialbackend.dto.request.FollowRequest;
import vn.pvhg.socialbackend.dto.response.FollowResponse;
import vn.pvhg.socialbackend.response.ApiPagedResponse;
import vn.pvhg.socialbackend.response.ApiResponse;
import vn.pvhg.socialbackend.service.FollowService;

import java.util.UUID;

@RestController
@RequestMapping("/api/follows")
@RequiredArgsConstructor
public class FollowController {

    private final FollowService followService;

    @PostMapping
    public ResponseEntity<Object> followUser(@RequestBody FollowRequest request) {
        followService.followUser(request);
        ApiResponse<Void> response = new ApiResponse<>(HttpStatus.OK, true, "Follow successfully", null);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/followings")
    public ResponseEntity<Object> getFollowings(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("followedAt").descending());
        Page<FollowResponse> followResponsePage = followService.getFollowings(pageable);
        ApiPagedResponse<FollowResponse> response = new ApiPagedResponse<>(
                HttpStatus.OK,
                true,
                null,
                followResponsePage.getContent(),
                followResponsePage.getNumber(),
                followResponsePage.getNumberOfElements(),
                followResponsePage.getTotalElements(),
                followResponsePage.getTotalPages());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/followers")
    public ResponseEntity<Object> getFollowers(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("followedAt").descending());
        Page<FollowResponse> followResponsePage = followService.getFollowers(pageable);
        ApiPagedResponse<FollowResponse> response = new ApiPagedResponse<>(
                HttpStatus.OK,
                true,
                null,
                followResponsePage.getContent(),
                followResponsePage.getNumber(),
                followResponsePage.getNumberOfElements(),
                followResponsePage.getTotalElements(),
                followResponsePage.getTotalPages());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Object> unfollowUser(@PathVariable UUID userId) {
        followService.unfollowUser(userId);
        ApiResponse<Void> response = new ApiResponse<>(HttpStatus.OK, true, "Follow successfully", null);
        return ResponseEntity.ok(response);
    }
}
