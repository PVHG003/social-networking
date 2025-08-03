package vn.pvhg.socialbackend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import vn.pvhg.socialbackend.dto.response.PostResponse;
import vn.pvhg.socialbackend.model.post.Post;
import vn.pvhg.socialbackend.model.post.PostMedia;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Mapper(componentModel = "spring")
public interface PostMapper {

    @Mapping(source = "user.profile.displayName", target = "username")
    @Mapping(source = "user.profile.profileImage", target = "profilePicture")
//    @Mapping(source = "postMedias", target = "postMedias", qualifiedByName = "postMediasToStringList")
    PostResponse toResponse(Post post);

    @Named("postMediasToStringList")
    default List<String> postMediasToStringList(List<PostMedia> postMedias) {
        if (postMedias == null) {
            return Collections.emptyList();
        }
        return postMedias.stream()
                .sorted(Comparator.comparingInt(PostMedia::getPosition))
                .map(PostMedia::getStoragePath)
                .toList();
    }
}
