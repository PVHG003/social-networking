package vn.pvhg.socialbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vn.pvhg.socialbackend.model.authentication.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);

    @Query("""
            select u from User u
            left join fetch u.profile up
            where up.handleName = :profileHandleName
            """)
    Optional<User> findByProfileHandleName(String profileHandleName);
}