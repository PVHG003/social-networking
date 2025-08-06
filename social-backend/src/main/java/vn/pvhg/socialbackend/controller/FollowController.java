package vn.pvhg.socialbackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.pvhg.socialbackend.dto.response.UserProfileResponse;
import vn.pvhg.socialbackend.response.ApiPagedResponse;
import vn.pvhg.socialbackend.response.ApiResponse;
import vn.pvhg.socialbackend.service.FollowService;

@RestController
@RequestMapping("/api/users/{handleName}")
@RequiredArgsConstructor
public class FollowController {
    private final FollowService followService;

    @PostMapping("/follow")
    public ResponseEntity<ApiResponse<Void>> follow(@PathVariable String handleName) {
        followService.followUser(handleName);
        ApiResponse<Void> response = new ApiResponse<>(HttpStatus.OK, true, "User followed", null);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/unfollow")
    public ResponseEntity<ApiResponse<Void>> unfollow(@PathVariable String handleName) {
        followService.unfollowUser(handleName);
        ApiResponse<Void> response = new ApiResponse<>(HttpStatus.OK, true, "User unfollowed", null);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/followers")
    public ResponseEntity<ApiPagedResponse<UserProfileResponse>> followers(
            @PathVariable String handleName,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "followedAt"));
        Page<UserProfileResponse> followers = followService.getFollowers(handleName, pageable);
        ApiPagedResponse<UserProfileResponse> response = new ApiPagedResponse<>(
                HttpStatus.OK,
                true,
                "Followers found",
                followers.getContent(),
                followers.getNumber(),
                followers.getNumberOfElements(),
                followers.getTotalElements(),
                followers.getTotalPages());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/followings")
    public ResponseEntity<ApiPagedResponse<UserProfileResponse>> followings(
            @PathVariable String handleName,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "followedAt"));
        Page<UserProfileResponse> followings = followService.getFollowings(handleName, pageable);
        ApiPagedResponse<UserProfileResponse> response = new ApiPagedResponse<>(
                HttpStatus.OK,
                true,
                "Followers found",
                followings.getContent(),
                followings.getNumber(),
                followings.getNumberOfElements(),
                followings.getTotalElements(),
                followings.getTotalPages());
        return ResponseEntity.ok(response);

    }
}