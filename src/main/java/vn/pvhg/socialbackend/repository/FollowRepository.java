package vn.pvhg.socialbackend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import vn.pvhg.socialbackend.model.authentication.User;
import vn.pvhg.socialbackend.model.friend.Follow;

import java.util.UUID;

public interface FollowRepository extends JpaRepository<Follow, UUID> {
    Page<Follow> findAllByFollowing(User following, Pageable pageable);

    Page<Follow> findAllByFollowed(User followed, Pageable pageable);

    boolean existsByFollowingAndFollowed(User currentUser, User userToFollow);
}