package vn.pvhg.socialbackend.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.stereotype.Service;
import vn.pvhg.socialbackend.dto.response.UserProfileResponse;
import vn.pvhg.socialbackend.mapper.FollowMapper;
import vn.pvhg.socialbackend.model.authentication.User;
import vn.pvhg.socialbackend.model.friend.Follow;
import vn.pvhg.socialbackend.repository.FollowRepository;
import vn.pvhg.socialbackend.repository.UserRepository;
import vn.pvhg.socialbackend.service.FollowService;
import vn.pvhg.socialbackend.utils.AuthUserUtils;

@Service
@RequiredArgsConstructor
public class FollowServiceImpl implements FollowService {
    private final FollowRepository followRepository;
    private final UserRepository userRepository;
    private final FollowMapper followMapper;
    private final AuthUserUtils authUserUtils;

    @Transactional
    @Override
    public void followUser(String handleName) {
        User currentUser = authUserUtils.getCurrentUser();
        User userToFollow = userRepository.findByProfileHandleName(handleName)
                .orElseThrow(() -> new EntityNotFoundException("User not found with handle name: " + handleName));

        if (currentUser.getId().equals(userToFollow.getId())) {
            throw new IllegalStateException("User cannot follow themselves");
        }

        if (followRepository.existsByFollowingAndFollowed(currentUser, userToFollow)) {
            throw new IllegalStateException("You are already following this user.");
        }

        Follow follow = new Follow();
        follow.setFollowing(currentUser);
        follow.setFollowed(userToFollow);
        followRepository.save(follow);
    }

    @Override
    public Page<UserProfileResponse> getFollowings(String handleName, Pageable pageable) {
        User user = userRepository.findByProfileHandleName(handleName)
                .orElseThrow(() -> new EntityNotFoundException("User not found with handle name: " + handleName));

        Page<Follow> followings = followRepository.findAllByFollowing(user, pageable);
        return followings.map(followMapper::toFollowingResponse);
    }

    @Override
    public Page<UserProfileResponse> getFollowers(String handleName, Pageable pageable) {
        User user = userRepository.findByProfileHandleName(handleName)
                .orElseThrow(() -> new EntityNotFoundException("User not found with handle name: " + handleName));

        Page<Follow> followers = followRepository.findAllByFollowed(user, pageable);
        return followers.map(followMapper::toFollowedResponse);
    }

    @Override
    public void unfollowUser(String handleName) {
        User currentUser = authUserUtils.getCurrentUser();
        User userToUnfollow = userRepository.findByProfileHandleName(handleName)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + handleName));

        Follow follow = followRepository.findByFollowingAndFollowed(currentUser, userToUnfollow)
                .orElseThrow(() -> new EntityNotFoundException("Follow relationship not found"));

        if (!follow.getFollowing().getId().equals(currentUser.getId())) {
            throw new AuthorizationDeniedException("You can only unfollow users you are following");
        }

        followRepository.deleteById(follow.getId());
    }
}
