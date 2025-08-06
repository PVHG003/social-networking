package vn.pvhg.socialbackend.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import vn.pvhg.socialbackend.model.authentication.RevokedToken;
import vn.pvhg.socialbackend.repository.RevokedTokenRepository;
import vn.pvhg.socialbackend.service.TokenRevocationService;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class TokenRevocationServiceImpl implements TokenRevocationService {
    private final RevokedTokenRepository revokedTokenRepository;

    @Override
    public void revokeToken(String tokenId, Instant expiryDate) {
        RevokedToken revokedToken = new RevokedToken(tokenId, expiryDate);
        revokedTokenRepository.save(revokedToken);
    }

    @Override
    public boolean isTokenRevoked(String tokenId) {
        RevokedToken revokedToken = revokedTokenRepository.findByTokenId(tokenId);
        return revokedToken != null;
    }

    @Scheduled(fixedRate = 600000) // Daily cleanup
    @Override
    public void cleanupExpiredTokens() {
        revokedTokenRepository.deleteByExpiryDateBefore(Instant.now());
    }
}
