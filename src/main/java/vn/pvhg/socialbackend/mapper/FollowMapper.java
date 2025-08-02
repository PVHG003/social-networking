package vn.pvhg.socialbackend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import vn.pvhg.socialbackend.dto.response.FollowResponse;
import vn.pvhg.socialbackend.model.friend.Follow;

@Mapper(componentModel = "spring")
public interface FollowMapper {
    @Mapping(target = "followingUser", source = "following.id")
    @Mapping(target = "followedUser", source = "followed.id")
    FollowResponse toResponse(Follow follow);
}
