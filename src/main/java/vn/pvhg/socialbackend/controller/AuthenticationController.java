package vn.pvhg.socialbackend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.pvhg.socialbackend.dto.request.LoginRequest;
import vn.pvhg.socialbackend.dto.request.RegisterRequest;
import vn.pvhg.socialbackend.dto.response.LoginResponse;
import vn.pvhg.socialbackend.service.AuthenticationService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<Object> register(@Valid @RequestBody RegisterRequest requestForm) {
        authenticationService.register(requestForm);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/verify")
    public ResponseEntity<Object> verify(@RequestParam String email) {
        authenticationService.verify(email);
        return ResponseEntity.ok("User verified successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequest requestForm) {
        LoginResponse response = authenticationService.login(requestForm);
        return ResponseEntity.ok(response);
    }

}
