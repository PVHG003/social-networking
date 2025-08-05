package vn.pvhg.socialbackend.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import vn.pvhg.socialbackend.dto.request.PostRequest;
import vn.pvhg.socialbackend.dto.response.PostResponse;
import vn.pvhg.socialbackend.mapper.PostMapper;
import vn.pvhg.socialbackend.model.authentication.User;
import vn.pvhg.socialbackend.model.post.Post;
import vn.pvhg.socialbackend.model.post.PostMedia;
import vn.pvhg.socialbackend.repository.PostMediaRepository;
import vn.pvhg.socialbackend.repository.PostRepository;
import vn.pvhg.socialbackend.security.UserDetailsServiceImpl;
import vn.pvhg.socialbackend.service.PostService;
import vn.pvhg.socialbackend.utils.AuthUserUtils;
import vn.pvhg.socialbackend.utils.FileUploadUtils;

import java.awt.*;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final FileUploadUtils fileUploadUtils;
    private final UserDetailsServiceImpl userDetailsServiceImpl;
    private final PostRepository postRepository;
    private final PostMediaRepository postMediaRepository;
    private final PostMapper postMapper;
    private final AuthUserUtils authUserUtils;

    @Transactional
    @Override
    public PostResponse createPost(PostRequest requestForm, List<MultipartFile> files) {
        User user = authUserUtils.getCurrentUser();

        Post post = new Post();
        post.setUser(user);
        post.setContent(requestForm.content());
        post.setPostMedias(new ArrayList<>());
        post = postRepository.save(post);
        post.setPostMedias(uploadMedias(post, files));
        return postMapper.toResponse(post);
    }

    private List<PostMedia> uploadMedias(Post post, List<MultipartFile> files) {
        Path userPostsPath = Path.of("users", post.getUser().getId().toString(), "posts", post.getId().toString());
        List<String> uploadedPaths = fileUploadUtils.storeFiles(userPostsPath, files);

        List<PostMedia> postMediaList = new ArrayList<>();
        int position = 0;

        for (String item : uploadedPaths) {
            Dimension dimension = fileUploadUtils.getDimension(item);

            PostMedia postMedia = new PostMedia();
            postMedia.setPost(post);
            postMedia.setOriginalFilename(fileUploadUtils.getOriginalFileName(item));
            postMedia.setStoragePath(item);
            postMedia.setFileSize(fileUploadUtils.getFileSize(item));
            postMedia.setMediaType(fileUploadUtils.getMediaType(item));
            postMedia.setMimeType(fileUploadUtils.getMimeType(item));
            postMedia.setPosition(position++);
            postMedia.setHeight(dimension != null ? dimension.getSize().height : null);
            postMedia.setWidth(dimension != null ? dimension.getSize().width : null);
            postMedia.setDuration(fileUploadUtils.getDuration(item));
            postMedia.setDeleted(false);

            postMediaList.add(postMedia);
        }
        postMediaRepository.saveAll(postMediaList);

        return postMediaList;
    }

    @Override
    public Page<PostResponse> getAllPosts(Pageable pageable) {
        Page<Post> posts = postRepository.findAllPostsWithMedias(pageable);
        return posts.map(postMapper::toResponse);
    }

    @Override
    public PostResponse getPostById(UUID id) {
        Post post = postRepository.findPostWithMediasById(id)
                .orElseThrow(() -> new EntityNotFoundException("Post not found with id " + id));
        return postMapper.toResponse(post);
    }

    @Override
    public Page<PostResponse> getUserPosts(UUID userId, Pageable pageable) {
        Page<Post> posts = postRepository.findPostsWithMediasByUserId(userId, pageable);
        return posts.map(postMapper::toResponse);
    }

    @Transactional
    @Override
    public void deletePost(UUID postId) {
        User user = authUserUtils.getCurrentUser();

        if (postRepository.existsByIdAndUser(postId, user)) {
            throw new AuthorizationDeniedException("You are not allowed to update this post");
        }

        Post post = postRepository.findPostWithMediasById(postId)
                .orElseThrow(() -> new EntityNotFoundException("Post not found with id " + postId));
        List<PostMedia> postMedia = post.getPostMedias();
        for (PostMedia media : postMedia) {
            try {
                fileUploadUtils.deleteDirectory(media.getStoragePath());
                fileUploadUtils.deleteFile(media.getStoragePath());
            } catch (FileNotFoundException e) {
                log.error("Failed to delete media file: {}", media.getStoragePath(), e);
            }
        }
        postRepository.deleteById(postId);
    }

    @Override
    public PostResponse updatePost(UUID postId, PostRequest req) {
        User user = authUserUtils.getCurrentUser();

        if (postRepository.existsByIdAndUser(postId, user)) {
            throw new AuthorizationDeniedException("You are not allowed to update this post");
        }

        Post post = postRepository.findById(postId).orElseThrow(() -> new EntityNotFoundException("Post not found with id " + postId));
        if (req.content() != null) {
            post.setContent(req.content());
        }
        postRepository.save(post);
        return postMapper.toResponse(post);
    }
}
