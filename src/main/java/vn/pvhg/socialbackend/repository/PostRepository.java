package vn.pvhg.socialbackend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.pvhg.socialbackend.model.post.Post;

import java.util.Optional;
import java.util.UUID;

public interface PostRepository extends JpaRepository<Post, UUID> {

    @Query("""
            select p from Post p
            left join fetch p.postMedias pm
            where p.id=:postId
            """)
    Optional<Post> findPostWithMediasById(UUID postId);

    @Query(value = """
            select p from Post p
            left join fetch p.postMedias pm
            order by p.createdAt desc
            """,
            countQuery = "select count(p) from Post p")
    Page<Post> findAllPostsWithMedias(Pageable pageable);

    @Query(value = """
            select p from Post p
            left join fetch p.postMedias pm
            where p.user.id=:userId
            order by p.createdAt desc
            """,
            countQuery = "select count(p) from Post p where p.user.id=:userId")
    Page<Post> findPostsWithMediasByUserId(@Param("userId") UUID userId, Pageable pageable);
}