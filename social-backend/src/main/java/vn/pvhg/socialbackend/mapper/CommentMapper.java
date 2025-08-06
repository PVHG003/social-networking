package vn.pvhg.socialbackend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import vn.pvhg.socialbackend.dto.response.CommentResponse;
import vn.pvhg.socialbackend.model.interaction.Comment;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mapping(target = "displayName", source = "user.profile.displayName")
    @Mapping(target = "handleName", source = "user.profile.handleName")
    @Mapping(target = "profileImage", source = "user.profile.profileImage")
    CommentResponse toDto(Comment comment);

}
