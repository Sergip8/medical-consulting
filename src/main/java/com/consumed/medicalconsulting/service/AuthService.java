package com.consumed.medicalconsulting.service;


import com.consumed.medicalconsulting.dto.*;
import com.consumed.medicalconsulting.exception.SpringRedditException;
import com.consumed.medicalconsulting.models.Enums.Role;
import com.consumed.medicalconsulting.models.User;
import com.consumed.medicalconsulting.models.VerificationToken;
import com.consumed.medicalconsulting.repository.UserRepository;
import com.consumed.medicalconsulting.repository.VerificationTokenRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository repository;
    private final VerificationTokenRepository verificationTokenRepository;
    private  final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final MailService mailService;
    private final RefreshTokenService refreshTokenService;

    @Transactional

    public VerificationToken register(RegisterRequest request) {
        var user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .created(Instant.now())
                .isActive(0)
                .build();
        repository.save(user);


        return generateVerificationToken(user);
    }

    public AuthenticationResponse login(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                ));
        var user = repository.findByEmail(request.getEmail()).orElseThrow();
        var jwt = jwtService.generateToken(user.getUsername());
        return AuthenticationResponse.builder()
                .authenticationToken(jwt)
                .refreshToken(refreshTokenService.generateRefreshToken().getToken())
                .expiresAt(jwtService.extractExpiration(jwt).toInstant())
                .email(request.getEmail())
                .build();
    }
    private VerificationToken generateVerificationToken(User user){
        var token = jwtService.generateToken(user.getEmail());
        var verificationToken = VerificationToken.builder()
                .token(token)
                .expiryDate(jwtService.extractExpiration(token).toInstant())
                .user(user)
                .build();
        verificationTokenRepository.save(verificationToken);
        sendMail(token);

        return verificationToken;
    }
    private void sendMail(String token){
        mailService.sendMail(new NotificationEmail(
                "Activa tu cuenta",
                jwtService.extractUsername(token),
                "Gracias por registrarse en esta mierda para activar su cuenta haga click aqui " +
                        "http://localhost:8080/api/v1/userAuth/accountVerification/" + token));
    }

    @Transactional
    public void verifyEmail(String token) {


       String username = jwtService.extractUsername(token);
       var user =  repository.findByEmail(username).orElseThrow(() -> new SpringRedditException("Invalid Token"));
       if(jwtService.isTokenValid(token, user)){
           user.setIsActive(1);
           repository.save(user);
       }

    }
    public AuthenticationResponse refreshToken(RefreshTokenRequest refreshToken) {
        refreshTokenService.validateRefreshToken(refreshToken.getRefreshToken());
        String token = jwtService.generateToken(refreshToken.getEmail());

        return AuthenticationResponse.builder()
                .authenticationToken(token)
                .refreshToken(refreshToken.getRefreshToken())
                .email(refreshToken.getEmail())
                .expiresAt(jwtService.extractExpiration(token).toInstant())
                .build();
    }
}
