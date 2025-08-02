package vn.pvhg.socialbackend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.pvhg.socialbackend.dto.request.FollowRequest;
import vn.pvhg.socialbackend.dto.response.FollowResponse;

import java.util.UUID;

public interface FollowService {

    void followUser(FollowRequest request);

    Page<FollowResponse> getFollowings(Pageable pageable);

    Page<FollowResponse> getFollowers(Pageable pageable);

    void unfollowUser(UUID followId);

    Page<FollowResponse> getUserFollowers(UUID userId, Pageable pageable);

    Page<FollowResponse> getUserFollowings(UUID userId, Pageable pageable);
}
