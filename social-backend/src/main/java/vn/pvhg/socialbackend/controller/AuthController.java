package vn.pvhg.socialbackend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<LoginResponse>> register(
            UriComponentsBuilder uriComponentsBuilder,
            @Valid @RequestBody RegisterRequest requestForm) {
        LoginResponse login = authenticationService.register(requestForm);
        ApiResponse<LoginResponse> response = new ApiResponse<>(HttpStatus.CREATED, true,
                "User registered successfully", login);
        URI uri = uriComponentsBuilder.path("/api/users/me").build().toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(@RequestBody LoginRequest requestForm) {
        LoginResponse loginResponse = authenticationService.login(requestForm);
        ApiResponse<LoginResponse> response = new ApiResponse<>(HttpStatus.CREATED, true, "User logged in successfully",
                loginResponse);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<Void>> logout() {
        authenticationService.logout();
        ApiResponse<Void> response = new ApiResponse<>(HttpStatus.OK, true, "User logged out", null);
        return ResponseEntity.ok(response);
    }

}
