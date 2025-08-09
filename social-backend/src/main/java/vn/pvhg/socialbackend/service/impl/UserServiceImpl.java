package vn.pvhg.socialbackend.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vn.pvhg.socialbackend.dto.request.ProfileRequest;
import vn.pvhg.socialbackend.dto.response.PostResponse;
import vn.pvhg.socialbackend.dto.response.ProfileResponse;
import vn.pvhg.socialbackend.mapper.PostMapper;
import vn.pvhg.socialbackend.mapper.ProfileMapper;
import vn.pvhg.socialbackend.model.UserProfile;
import vn.pvhg.socialbackend.model.authentication.User;
import vn.pvhg.socialbackend.model.post.Post;
import vn.pvhg.socialbackend.repository.PostRepository;
import vn.pvhg.socialbackend.repository.UserProfileRepository;
import vn.pvhg.socialbackend.service.UserService;
import vn.pvhg.socialbackend.utils.AuthUserUtils;
import vn.pvhg.socialbackend.utils.FileUploadUtils;

import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final AuthUserUtils authUserUtils;
    private final FileUploadUtils fileUploadUtils;
    private final ProfileMapper profileMapper;
    private final UserProfileRepository userProfileRepository;
    private final PostRepository postRepository;
    private final PostMapper postMapper;

    @Override
    public ProfileResponse getUserProfile(String handleName) {
        UserProfile userProfile = userProfileRepository.findByHandleName(handleName);
        if (userProfile == null) {
            throw new EntityNotFoundException("Profile not found");
        }
        return profileMapper.toResponse(userProfile);
    }

    @Override
    public ProfileResponse updateProfile(ProfileRequest request, MultipartFile file) {
        User user = authUserUtils.getCurrentUser();
        UserProfile userProfile = user.getProfile();

        if (userProfile == null) {
            userProfile = new UserProfile();
            userProfile.setUser(user);
        }

        userProfile.setDisplayName(request.displayName());
        userProfile.setBio(request.bio());
        userProfile.setLocation(request.location());
        userProfile.setBirthday(request.birthday());
        userProfile.setGender(request.gender());

        Path path = Path.of("users", user.getId().toString(), "profiles");
        List<String> profileImage = fileUploadUtils.storeFiles(path, Collections.singletonList(file));
        userProfile.setProfileImage(profileImage.getLast());

        userProfileRepository.save(userProfile);

        return profileMapper.toResponse(userProfile);
    }

    @Override
    public List<PostResponse> getUserPosts(String handleName) {
        UserProfile userProfile = userProfileRepository.findByHandleName(handleName);
        if (userProfile == null) {
            throw new EntityNotFoundException("User profile not found");
        }
        User user = userProfile.getUser();
        List<Post> posts = postRepository.findPostByUser(user);
        return posts.stream().map(postMapper::toResponse)
                .toList();
    }
}
