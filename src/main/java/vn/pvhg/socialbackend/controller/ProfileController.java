package vn.pvhg.socialbackend.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.pvhg.socialbackend.dto.request.ProfileRequest;
import vn.pvhg.socialbackend.dto.response.ProfileResponse;
import vn.pvhg.socialbackend.response.ApiResponse;
import vn.pvhg.socialbackend.service.ProfileService;

@Slf4j
@RestController
@RequestMapping("/api/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping
    public ResponseEntity<Object> getProfile() {
        ProfileResponse profileResponse = profileService.getProfile();
        ApiResponse<ProfileResponse> response = new ApiResponse<>(HttpStatus.OK, true, "Get profile", profileResponse);
        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<Object> updateProfile(@RequestBody ProfileRequest requestForm) {
        ProfileResponse profileResponse = profileService.updateProfile(requestForm);
        ApiResponse<ProfileResponse> response = new ApiResponse<>(HttpStatus.OK, true, "Update profile", profileResponse);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Object> uploadProfileImage(@RequestPart("file") MultipartFile file) {
        ProfileResponse profileResponse = profileService.uploadProfileImage(file);
        ApiResponse<ProfileResponse> response = new ApiResponse<>(HttpStatus.OK, true, "Upload profile image", profileResponse);
        return ResponseEntity.ok(response);
    }
}
