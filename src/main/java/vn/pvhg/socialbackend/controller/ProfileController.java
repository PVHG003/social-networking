package vn.pvhg.socialbackend.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.pvhg.socialbackend.dto.request.ProfileRequest;
import vn.pvhg.socialbackend.dto.response.ProfileResponse;
import vn.pvhg.socialbackend.service.ProfileService;

@Slf4j
@RestController
@RequestMapping("/api/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping
    public ResponseEntity<Object> getProfile() {
        ProfileResponse response = profileService.getProfile();
        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<Object> updateProfile(@RequestBody ProfileRequest requestForm) {
        ProfileResponse response = profileService.updateProfile(requestForm);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Object> uploadProfileImage(@RequestParam("file") MultipartFile file) {
        ProfileResponse response = profileService.uploadProfileImage(file);
        return ResponseEntity.ok(response);
    }

}
