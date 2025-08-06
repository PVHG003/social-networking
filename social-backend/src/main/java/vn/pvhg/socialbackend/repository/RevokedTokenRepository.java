package vn.pvhg.socialbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.pvhg.socialbackend.model.authentication.RevokedToken;

import java.time.Instant;

@Repository
public interface RevokedTokenRepository extends JpaRepository<RevokedToken, String> {
    RevokedToken findByTokenId(String tokenId);

    void deleteByExpiryDateBefore(Instant now);
}