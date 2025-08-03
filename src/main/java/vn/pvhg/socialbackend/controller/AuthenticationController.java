package vn.pvhg.socialbackend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import vn.pvhg.socialbackend.dto.request.LoginRequest;
import vn.pvhg.socialbackend.dto.request.RegisterRequest;
import vn.pvhg.socialbackend.dto.response.LoginResponse;
import vn.pvhg.socialbackend.response.ApiResponse;
import vn.pvhg.socialbackend.service.AuthenticationService;

import java.net.URI;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<Object> register(
            UriComponentsBuilder uriComponentsBuilder,
            @Valid @RequestBody RegisterRequest requestForm) {
        authenticationService.register(requestForm);
        ApiResponse<Void> response = new ApiResponse<>(HttpStatus.CREATED, true, "User registered successfully", null);
        URI uri = uriComponentsBuilder.path("/api/auth/login").build().toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @PostMapping("/verify")
    public ResponseEntity<Object> verify(@RequestParam String email) {
        authenticationService.verify(email);
        ApiResponse<Void> response = new ApiResponse<>(HttpStatus.CREATED, true, "User verified successfully", null);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequest requestForm) {
        LoginResponse loginResponse = authenticationService.login(requestForm);
        ApiResponse<LoginResponse> response = new ApiResponse<>(HttpStatus.CREATED, true, "User logged in successfully", loginResponse);
        return ResponseEntity.ok(response);
    }

}
