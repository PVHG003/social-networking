package vn.pvhg.socialbackend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.pvhg.socialbackend.model.authentication.User;
import vn.pvhg.socialbackend.model.interaction.Like;
import vn.pvhg.socialbackend.model.post.Post;

import java.util.UUID;

@Repository
public interface LikeRepository extends JpaRepository<Like, UUID> {
    boolean existsByUserAndPost(User user, Post post);

    void deleteByUserAndPost(User user, Post post);

    Page<Like> findAllByPostId(UUID postId, Pageable pageable);
}