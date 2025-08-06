package vn.pvhg.socialbackend.service;

import org.springframework.web.multipart.MultipartFile;
import vn.pvhg.socialbackend.dto.request.ProfileRequest;
import vn.pvhg.socialbackend.dto.response.ProfileResponse;

public interface UserService {

    ProfileResponse getUserProfile(String handleName);

    ProfileResponse updateProfile(ProfileRequest profileRequest, MultipartFile file);
}
