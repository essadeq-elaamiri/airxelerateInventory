package com.airxelerate.airxelerateInventory.controller;

import com.airxelerate.airxelerateInventory.dto.JwtResponse;
import com.airxelerate.airxelerateInventory.dto.LoginRequest;
import com.airxelerate.airxelerateInventory.dto.SignupRequest;
import com.airxelerate.airxelerateInventory.entity.AppUser;
import com.airxelerate.airxelerateInventory.enums.UserRole;
import com.airxelerate.airxelerateInventory.repository.AppUserRepository;
import com.airxelerate.airxelerateInventory.utils.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationManager authManager;
    private final AppUserRepository repo;
    private final PasswordEncoder encoder;
    private final JwtUtil jwtUtil;


    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest req) {
        if (repo.existsByEmail(req.email())) {
            return ResponseEntity.badRequest().body("Email already in use");
        }
        AppUser user = AppUser.builder()
                .fullName(req.fullName())
                .email(req.email())
                .password(encoder.encode(req.password()))
                .userRole(req.userRole())
                .build();
        repo.save(user);
        return ResponseEntity.ok("User Registered");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {
        try {
            Authentication auth = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(req.email(), req.password()));
            UserDetails userDetails = (UserDetails) auth.getPrincipal();

            String token = jwtUtil.generateToken(userDetails.getUsername());
            return ResponseEntity.ok(new JwtResponse(token));
        } catch (AuthenticationException ex) {
            log.error("AuthenticationException : {}", ex.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
        catch (Exception e) {
            log.error("Exception : {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication problem");
        }
    }
}
