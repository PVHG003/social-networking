package vn.pvhg.socialbackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.pvhg.socialbackend.dto.request.ProfileRequest;
import vn.pvhg.socialbackend.dto.response.ProfileResponse;
import vn.pvhg.socialbackend.response.ApiResponse;
import vn.pvhg.socialbackend.service.UserService;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{handleName}")
    public ResponseEntity<ApiResponse<ProfileResponse>> getProfile(@PathVariable String handleName) {
        ProfileResponse profile = userService.getUserProfile(handleName);
        ApiResponse<ProfileResponse> response = new ApiResponse<>(HttpStatus.OK, true, "User profile found", profile);
        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "/me", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiResponse<ProfileResponse>> updateProfile(@RequestPart(name = "data") ProfileRequest profileRequest,
                                                                      @RequestPart(name = "file", required = false) MultipartFile file) {
        ProfileResponse profile = userService.updateProfile(profileRequest, file);
        ApiResponse<ProfileResponse> response = new ApiResponse<>(HttpStatus.OK, true, "User profile found", profile);
        return ResponseEntity.ok(response);
    }
}