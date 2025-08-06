package vn.pvhg.socialbackend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.pvhg.socialbackend.model.interaction.Comment;
import vn.pvhg.socialbackend.model.post.Post;

import java.util.UUID;

@Repository
public interface CommentRepository extends JpaRepository<Comment, UUID> {
    Page<Comment> findAllByPost(Post post, Pageable pageable);
}