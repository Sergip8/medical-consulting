package com.consumed.medicalconsulting.service;

import com.consumed.medicalconsulting.exception.SpringRedditException;
import com.consumed.medicalconsulting.models.RefreshToken;
import com.consumed.medicalconsulting.repository.RefreshTokenRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;
@Service
@AllArgsConstructor
@Transactional
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    public RefreshToken generateRefreshToken(){
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setCreatedDate(Instant.now());
        return refreshTokenRepository.save(refreshToken);
    }
    public void validateRefreshToken(String refreshToken){
        System.out.println(refreshToken);
        refreshTokenRepository.findByToken(refreshToken).orElseThrow(() -> new SpringRedditException("Token invalido"));

    }
    public void  deleteRefreshToken(String refreshToken){
        refreshTokenRepository.deleteByToken(refreshToken);
    }
}
