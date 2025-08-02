package vn.pvhg.socialbackend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;
import vn.pvhg.socialbackend.dto.request.PostRequest;
import vn.pvhg.socialbackend.dto.response.PostResponse;

import java.util.List;
import java.util.UUID;

public interface PostService {
    PostResponse createPost(PostRequest requestForm);

    PostResponse uploadMedias(UUID id, List<MultipartFile> files);

    Page<PostResponse> getAllPosts(Pageable pageable);

    PostResponse getPostById(UUID id);

    Page<PostResponse> getUserPosts(UUID userId, Pageable pageable);

    void deletePost(UUID id);
}
