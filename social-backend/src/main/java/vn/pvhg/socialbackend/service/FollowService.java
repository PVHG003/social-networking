package vn.pvhg.socialbackend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.pvhg.socialbackend.dto.response.UserProfileResponse;

public interface FollowService {

    void followUser(String handleName);

    void unfollowUser(String handleName);

    Page<UserProfileResponse> getFollowers(String handleName, Pageable pageable);

    Page<UserProfileResponse> getFollowings(String handleName, Pageable pageable);
}
