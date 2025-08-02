package vn.pvhg.socialbackend.service;

import vn.pvhg.socialbackend.dto.request.LoginRequest;
import vn.pvhg.socialbackend.dto.request.RegisterRequest;
import vn.pvhg.socialbackend.dto.response.LoginResponse;

public interface AuthenticationService {
    void register(RegisterRequest requestForm);

    void verify(String email);

    LoginResponse login(LoginRequest requestForm);
}
