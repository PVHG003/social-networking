package vn.pvhg.socialbackend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import vn.pvhg.socialbackend.dto.response.LikeResponse;
import vn.pvhg.socialbackend.model.interaction.Like;

@Mapper(componentModel = "spring")
public interface LikeMapper {
    @Mapping(target = "displayName", source = "user.profile.displayName")
    @Mapping(target = "handleName", source = "user.profile.handleName")
    LikeResponse toDto(Like like);
}
