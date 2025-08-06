package vn.pvhg.socialbackend.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.pvhg.socialbackend.dto.response.LikeResponse;
import vn.pvhg.socialbackend.mapper.LikeMapper;
import vn.pvhg.socialbackend.model.authentication.User;
import vn.pvhg.socialbackend.model.interaction.Like;
import vn.pvhg.socialbackend.model.post.Post;
import vn.pvhg.socialbackend.repository.LikeRepository;
import vn.pvhg.socialbackend.repository.PostRepository;
import vn.pvhg.socialbackend.service.LikeService;
import vn.pvhg.socialbackend.utils.AuthUserUtils;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {
    private final AuthUserUtils authUserUtils;
    private final PostRepository postRepository;
    private final LikeRepository likeRepository;
    private final LikeMapper likeMapper;

    @Override
    public void likePost(UUID postId) {
        User currentUser = authUserUtils.getCurrentUser();

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("Post not found with id: " + postId));

        if (likeRepository.existsByUserAndPost(currentUser, post)) {
            throw new IllegalStateException("Post already liked!");
        }

        Like like = new Like();
        like.setUser(currentUser);
        like.setPost(post);

        likeRepository.save(like);
    }

    @Override
    public void unlikePost(UUID postId) {
        User currentUser = authUserUtils.getCurrentUser();

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("Post not found with id: " + postId));

        if (!likeRepository.existsByUserAndPost(currentUser, post)) {
            throw new IllegalStateException("Post not liked!");
        }

        likeRepository.deleteByUserAndPost(currentUser, post);
    }

    @Override
    public Page<LikeResponse> getPostLikes(UUID postId, Pageable pageable) {
        Page<Like> postLikes = likeRepository.findAllByPostId(postId, pageable);
        return postLikes.map(likeMapper::toDto);
    }
}
