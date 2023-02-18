package com.consumed.medicalconsulting.controllers;

import com.consumed.medicalconsulting.dto.AuthRequest;
import com.consumed.medicalconsulting.dto.AuthenticationResponse;
import com.consumed.medicalconsulting.dto.RefreshTokenRequest;
import com.consumed.medicalconsulting.dto.RegisterRequest;
import com.consumed.medicalconsulting.models.VerificationToken;
import com.consumed.medicalconsulting.service.AuthService;
import com.consumed.medicalconsulting.service.RefreshTokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/userAuth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final RefreshTokenService refreshTokenService;
    @PostMapping("/register")
    public ResponseEntity<VerificationToken> register(@RequestBody RegisterRequest request){

        return  ResponseEntity.ok( authService.register(request));
    }
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthRequest request){

        return ResponseEntity.ok(authService.login(request));

    }
    @GetMapping("accountVerification/{token}")
    public ResponseEntity<String> verifyEmail(@PathVariable String token){
        authService.verifyEmail(token);
        return ResponseEntity.ok("Account Verified");
    }

    @PostMapping("refresh/token")
    public AuthenticationResponse refreshToken(@Valid @RequestBody RefreshTokenRequest refreshToken){
        System.out.println(refreshToken);
        return authService.refreshToken(refreshToken);
    }
    @PostMapping("logout")
    public ResponseEntity<String> logout(@Valid @RequestBody RefreshTokenRequest request){
        refreshTokenService.deleteRefreshToken(request.getRefreshToken());
        return ResponseEntity.ok("Refresh token borrao");
    }

}

