package vn.pvhg.socialbackend.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import vn.pvhg.socialbackend.dto.request.ProfileRequest;
import vn.pvhg.socialbackend.dto.response.PostResponse;
import vn.pvhg.socialbackend.dto.response.ProfileResponse;

public interface UserService {

    ProfileResponse getUserProfile(String handleName);

    ProfileResponse updateProfile(ProfileRequest profileRequest, MultipartFile file);

    List<PostResponse> getUserPosts(String handleName);
}
