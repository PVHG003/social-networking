package vn.pvhg.socialbackend.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vn.pvhg.socialbackend.dto.request.ProfileRequest;
import vn.pvhg.socialbackend.dto.response.ProfileResponse;
import vn.pvhg.socialbackend.mapper.ProfileMapper;
import vn.pvhg.socialbackend.model.UserProfile;
import vn.pvhg.socialbackend.model.authentication.User;
import vn.pvhg.socialbackend.repository.UserProfileRepository;
import vn.pvhg.socialbackend.security.UserDetailsImpl;
import vn.pvhg.socialbackend.security.UserDetailsServiceImpl;
import vn.pvhg.socialbackend.service.ProfileService;
import vn.pvhg.socialbackend.utils.FileUploadUtils;

import java.nio.file.Path;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    private final ProfileMapper profileMapper;
    private final UserProfileRepository userProfileRepository;
    private final UserDetailsServiceImpl userDetailsServiceImpl;
    private final FileUploadUtils fileUploadUtils;

    @Override
    public ProfileResponse getProfile() {
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetailsImpl userDetails = (UserDetailsImpl) userDetailsServiceImpl.loadUserByUsername(jwt.getSubject());
        UserProfile userProfile = userDetails.getUserProfile();

        return profileMapper.toResponse(userProfile);

    }

    @Override
    public ProfileResponse updateProfile(ProfileRequest requestForm) {
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetailsImpl userDetails = (UserDetailsImpl) userDetailsServiceImpl.loadUserByUsername(jwt.getSubject());
        UserProfile userProfile = userDetails.getUserProfile();

        if (userProfile == null) {
            userProfile = new UserProfile();
            userProfile.setUser(userDetails.getUser());
        }

        if (requestForm.displayName() != null) userProfile.setDisplayName(requestForm.displayName());
        if (requestForm.bio() != null) userProfile.setBio(requestForm.bio());
        if (requestForm.location() != null) userProfile.setLocation(requestForm.location());
        if (requestForm.websiteUrl() != null) userProfile.setWebsiteUrl(requestForm.websiteUrl());
        if (requestForm.birthdate() != null) userProfile.setBirthdate(requestForm.birthdate());
        if (requestForm.gender() != null) userProfile.setGender(requestForm.gender());

        userProfileRepository.save(userProfile);

        return profileMapper.toResponse(userProfile);
    }

    @Override
    public ProfileResponse uploadProfileImage(MultipartFile file) {
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetailsImpl userDetails = (UserDetailsImpl) userDetailsServiceImpl.loadUserByUsername(jwt.getSubject());
        User user = userDetails.getUser();

        Path userProfilePath = Path.of("users", user.getId().toString(), "profile");
        List<String> uploadedPath = fileUploadUtils.storeFiles(userProfilePath, List.of(file));
        UserProfile userProfile = user.getProfile();
        userProfile.setProfileImage(uploadedPath.getFirst());
        return profileMapper.toResponse(userProfileRepository.save(userProfile));
    }

}
