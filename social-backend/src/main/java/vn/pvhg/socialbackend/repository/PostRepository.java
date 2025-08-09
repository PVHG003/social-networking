package vn.pvhg.socialbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.pvhg.socialbackend.model.authentication.User;
import vn.pvhg.socialbackend.model.post.Post;

import java.util.List;
import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<Post, UUID> {

    boolean existsByIdAndUser(UUID postId, User user);

    List<Post> findPostByUser(User user);
}