package vn.pvhg.socialbackend.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import vn.pvhg.socialbackend.dto.request.FollowRequest;
import vn.pvhg.socialbackend.dto.response.FollowResponse;
import vn.pvhg.socialbackend.mapper.FollowMapper;
import vn.pvhg.socialbackend.model.authentication.User;
import vn.pvhg.socialbackend.model.friend.Follow;
import vn.pvhg.socialbackend.repository.FollowRepository;
import vn.pvhg.socialbackend.repository.UserRepository;
import vn.pvhg.socialbackend.security.UserDetailsImpl;
import vn.pvhg.socialbackend.security.UserDetailsServiceImpl;
import vn.pvhg.socialbackend.service.FollowService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FollowServiceImpl implements FollowService {
    private final UserDetailsServiceImpl userDetailsServiceImpl;
    private final FollowRepository followRepository;
    private final UserRepository userRepository;
    private final FollowMapper followMapper;

    @Transactional
    @Override
    public void followUser(FollowRequest request) {
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetailsImpl userDetails = (UserDetailsImpl) userDetailsServiceImpl.loadUserByUsername(jwt.getSubject());
        User currentUser = userDetails.getUser();
        User userToFollow = userRepository.findById(request.userId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + request.userId()));

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
    public Page<FollowResponse> getFollowings(Pageable pageable) {
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetailsImpl userDetails = (UserDetailsImpl) userDetailsServiceImpl.loadUserByUsername(jwt.getSubject());
        User currentUser = userDetails.getUser();

        Page<Follow> followings = followRepository.findAllByFollowing(currentUser, pageable);
        return followings.map(followMapper::toResponse);
    }

    @Override
    public Page<FollowResponse> getFollowers(Pageable pageable) {
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetailsImpl userDetails = (UserDetailsImpl) userDetailsServiceImpl.loadUserByUsername(jwt.getSubject());
        User currentUser = userDetails.getUser();

        Page<Follow> followers = followRepository.findAllByFollowed(currentUser, pageable);
        return followers.map(followMapper::toResponse);
    }

    @Override
    public void unfollowUser(UUID followId) {
        followRepository.deleteById(followId);
    }

    @Override
    public Page<FollowResponse> getUserFollowers(UUID userId, Pageable pageable) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));
        Page<Follow> followers = followRepository.findAllByFollowed(user, pageable);
        return followers.map(followMapper::toResponse);
    }

    @Override
    public Page<FollowResponse> getUserFollowings(UUID userId, Pageable pageable) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));
        Page<Follow> followers = followRepository.findAllByFollowing(user, pageable);
        return followers.map(followMapper::toResponse);
    }
}
