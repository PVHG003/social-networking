package vn.pvhg.socialbackend.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.stereotype.Service;
import vn.pvhg.socialbackend.dto.request.CommentRequest;
import vn.pvhg.socialbackend.dto.response.CommentResponse;
import vn.pvhg.socialbackend.mapper.CommentMapper;
import vn.pvhg.socialbackend.model.authentication.User;
import vn.pvhg.socialbackend.model.interaction.Comment;
import vn.pvhg.socialbackend.model.post.Post;
import vn.pvhg.socialbackend.repository.CommentRepository;
import vn.pvhg.socialbackend.repository.PostRepository;
import vn.pvhg.socialbackend.service.CommentService;
import vn.pvhg.socialbackend.utils.AuthUserUtils;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final AuthUserUtils authUserUtils;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    @Override
    public Page<CommentResponse> getPostComments(UUID postId, Pageable pageable) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("Post not found"));
        Page<Comment> comments = commentRepository.findAllByPost(post, pageable);
        return comments.map(commentMapper::toDto);
    }

    @Transactional
    @Override
    public CommentResponse postComment(UUID postId, CommentRequest request) {
        User user = authUserUtils.getCurrentUser();
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("Post not found"));

        Comment comment = new Comment();
        comment.setPost(post);
        comment.setUser(user);
        comment.setContent(request.content());

        comment = commentRepository.save(comment);
        return commentMapper.toDto(comment);
    }

    @Override
    public CommentResponse getCommentById(UUID commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("Comment not found"));
        return commentMapper.toDto(comment);
    }

    @Override
    public CommentResponse updateComment(UUID commentId, CommentRequest request) {
        User user = authUserUtils.getCurrentUser();
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("Comment not found"));

        if (!user.getId().equals(comment.getUser().getId())) {
            throw new AuthorizationDeniedException("You are not allowed to update this comment");
        }

        comment.setContent(request.content());
        comment = commentRepository.save(comment);
        return commentMapper.toDto(comment);
    }

    @Override
    public void deleteComment(UUID commentId) {
        User user = authUserUtils.getCurrentUser();
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("Comment not found"));

        if (!user.getId().equals(comment.getUser().getId())) {
            throw new AuthorizationDeniedException("You are not allowed to update this comment");
        }

        commentRepository.delete(comment);
    }
}
