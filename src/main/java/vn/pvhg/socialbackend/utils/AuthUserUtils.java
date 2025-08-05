package vn.pvhg.socialbackend.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import vn.pvhg.socialbackend.model.authentication.User;
import vn.pvhg.socialbackend.security.UserDetailsImpl;
import vn.pvhg.socialbackend.security.UserDetailsServiceImpl;

@Component
@RequiredArgsConstructor
public class AuthUserUtils {

    private final UserDetailsServiceImpl userDetailsServiceImpl;

    public User getCurrentUser() {
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetailsImpl userDetails = (UserDetailsImpl) userDetailsServiceImpl.loadUserByUsername(jwt.getSubject());
        return userDetails.getUser();
    }

}
