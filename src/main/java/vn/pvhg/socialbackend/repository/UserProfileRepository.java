package vn.pvhg.socialbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.pvhg.socialbackend.model.UserProfile;
import vn.pvhg.socialbackend.model.authentication.User;

import java.util.Optional;
import java.util.UUID;

public interface UserProfileRepository extends JpaRepository<UserProfile, UUID> {
    Optional<UserProfile> findByUser(User user);
}