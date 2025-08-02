package vn.pvhg.socialbackend.service;

import org.springframework.web.multipart.MultipartFile;
import vn.pvhg.socialbackend.dto.request.ProfileRequest;
import vn.pvhg.socialbackend.dto.response.ProfileResponse;

public interface ProfileService {
    ProfileResponse getProfile();

    ProfileResponse updateProfile(ProfileRequest requestForm);

    ProfileResponse uploadProfileImage(MultipartFile file);
}
