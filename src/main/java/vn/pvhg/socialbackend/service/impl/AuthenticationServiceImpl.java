package vn.pvhg.socialbackend.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import vn.pvhg.socialbackend.dto.request.LoginRequest;
import vn.pvhg.socialbackend.dto.request.RegisterRequest;
import vn.pvhg.socialbackend.dto.response.LoginResponse;
import vn.pvhg.socialbackend.exception.EmailAlreadyExistsException;
import vn.pvhg.socialbackend.model.authentication.RevokedToken;
import vn.pvhg.socialbackend.model.authentication.User;
import vn.pvhg.socialbackend.repository.RevokedTokenRepository;
import vn.pvhg.socialbackend.repository.UserRepository;
import vn.pvhg.socialbackend.service.AuthenticationService;
import vn.pvhg.socialbackend.utils.JwtUtils;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final AuthenticationProvider authenticationProvider;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final RevokedTokenRepository revokedTokenRepository;

    @Override
    public void register(RegisterRequest requestForm) {
        if (userRepository.findByEmail(requestForm.email()).isPresent()) {
            throw new EmailAlreadyExistsException("Email already in use: " + requestForm.email());
        }

        User user = new User();
        user.setEmail(requestForm.email());
        user.setPassword(passwordEncoder.encode(requestForm.password()));
        user.setRole("USER");
        userRepository.save(user);
    }

    @Override
    public void verify(String email) {
        userRepository.findByEmail(email).ifPresent(user -> {
            user.setEnabled(true);
            userRepository.save(user);
        });
    }

    @Override
    public LoginResponse login(LoginRequest requestForm) {
        Authentication authentication = authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(
                requestForm.email(), requestForm.password()
        ));

        String token = jwtUtils.generateToken(authentication);
        return new LoginResponse(
                token
        );
    }

    @Override
    public void logout() {
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        RevokedToken revokedToken = new RevokedToken();
        revokedToken.setTokenId(jwt.getId());
        revokedToken.setExpiryDate(jwt.getExpiresAt());
        revokedTokenRepository.save(revokedToken);
    }
}
