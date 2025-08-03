package vn.pvhg.socialbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.pvhg.socialbackend.model.authentication.RevokedToken;

import java.time.Instant;

public interface RevokedTokenRepository extends JpaRepository<RevokedToken, String> {
    RevokedToken findByTokenId(String tokenId);

    void deleteByExpiryDateBefore(Instant now);
}