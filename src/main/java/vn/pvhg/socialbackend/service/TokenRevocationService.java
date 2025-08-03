package vn.pvhg.socialbackend.service;

import java.time.Instant;

public interface TokenRevocationService {
    void revokeToken(String tokenId, Instant expiryDate);

    boolean isTokenRevoked(String tokenId);

    void cleanupExpiredTokens();
}
