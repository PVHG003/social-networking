package vn.pvhg.socialbackend.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.pvhg.socialbackend.dto.request.FollowRequest;
import vn.pvhg.socialbackend.dto.response.FollowResponse;
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
        return ResponseEntity.ok("User has been followed.");
    }

    @GetMapping("/followings")
    public ResponseEntity<Page<FollowResponse>> getFollowings(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("followedAt").descending());
        Page<FollowResponse> response = followService.getFollowings(pageable);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/followers")
    public ResponseEntity<Page<FollowResponse>> getFollowers(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("followedAt").descending());
        Page<FollowResponse> response = followService.getFollowers(pageable);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{followId}")
    public ResponseEntity<Object> unfollowUser(@PathVariable UUID followId) {
        followService.unfollowUser(followId);
        return ResponseEntity.ok("User has been unfollowed.");
    }
}
