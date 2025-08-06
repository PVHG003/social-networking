package vn.pvhg.socialbackend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import vn.pvhg.socialbackend.dto.response.UserProfileResponse;
import vn.pvhg.socialbackend.model.friend.Follow;

@Mapper(componentModel = "spring")
public interface FollowMapper {

    @Mapping(target = "id", source = "followed.profile.id")
    @Mapping(target = "handleName", source = "followed.profile.handleName")
    @Mapping(target = "displayName", source = "followed.profile.displayName")
    @Mapping(target = "gender", source = "followed.profile.gender")
    @Mapping(target = "profileImage", source = "followed.profile.profileImage")
    @Mapping(target = "bio", source = "followed.profile.bio")
    UserProfileResponse toFollowedResponse(Follow follow);


    @Mapping(target = "id", source = "following.profile.id")
    @Mapping(target = "handleName", source = "following.profile.handleName")
    @Mapping(target = "displayName", source = "following.profile.displayName")
    @Mapping(target = "gender", source = "following.profile.gender")
    @Mapping(target = "profileImage", source = "following.profile.profileImage")
    @Mapping(target = "bio", source = "following.profile.bio")
    UserProfileResponse toFollowingResponse(Follow follow);
}
