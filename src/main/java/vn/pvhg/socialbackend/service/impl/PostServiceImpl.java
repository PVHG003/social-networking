package vn.pvhg.socialbackend.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
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
import vn.pvhg.socialbackend.security.UserDetailsImpl;
import vn.pvhg.socialbackend.security.UserDetailsServiceImpl;
import vn.pvhg.socialbackend.service.PostService;
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

    @Transactional
    @Override
    public PostResponse createPost(PostRequest requestForm) {
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetailsImpl userDetails = (UserDetailsImpl) userDetailsServiceImpl.loadUserByUsername(jwt.getSubject());
        User user = userDetails.getUser();

        Post post = new Post();
        post.setUser(user);
        post.setContent(requestForm.content());
        post.setPostMedias(new ArrayList<>());
        postRepository.save(post);
        return postMapper.toResponse(post);
    }

    @Transactional
    @Override
    public PostResponse uploadMedias(UUID postId, List<MultipartFile> files) {
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetailsImpl userDetails = (UserDetailsImpl) userDetailsServiceImpl.loadUserByUsername(jwt.getSubject());
        User user = userDetails.getUser();

        Path userPostsPath = Path.of("users", user.getId().toString(), "posts", postId.toString());
        List<String> uploadedPaths = fileUploadUtils.storeFiles(userPostsPath, files);

        Post post = postRepository.findPostWithMediasById(postId)
                .orElseThrow(() -> new EntityNotFoundException("Post not found with id " + postId));

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

        return postMapper.toResponse(post);
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
    public void deletePost(UUID id) {
        Post post = postRepository.findPostWithMediasById(id)
                .orElseThrow(() -> new EntityNotFoundException("Post not found with id " + id));
        List<PostMedia> postMedia = post.getPostMedias();
        for (PostMedia media : postMedia) {
            try {
                fileUploadUtils.deleteDirectory(media.getStoragePath());
                fileUploadUtils.deleteFile(media.getStoragePath());
            } catch (FileNotFoundException e) {
                log.error("Failed to delete media file: {}", media.getStoragePath(), e);
            }
        }
        postRepository.deleteById(id);
    }
}
