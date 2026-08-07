package com.hall.VenueMgmt.auth.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hall.VenueMgmt.auth.dto.AuthResponse;
import com.hall.VenueMgmt.auth.dto.LoginRequest;
import com.hall.VenueMgmt.auth.dto.RegisterRequest;
import com.hall.VenueMgmt.auth.security.JwtUtils;
import com.hall.VenueMgmt.auth.service.AuthService;
import com.hall.VenueMgmt.exception.ResourceAlreadyExistsException;
import com.hall.VenueMgmt.user.entity.User;
import com.hall.VenueMgmt.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils; // Injected instance

    @Override
    public AuthResponse register(RegisterRequest request) {

        // Check if email already exists
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new ResourceAlreadyExistsException("Email already exists");
        }

        // Check if phone already exists
        if (userRepository.existsByPhone(request.getPhone())) {
            throw new ResourceAlreadyExistsException("Phone number already exists");
        }

        User user = new User();

        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());

        // Encrypt password
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        user.setRole(request.getRole());
        user.setActive(true);

        userRepository.save(user);

        return AuthResponse.builder()
                .message("User registered successfully")
                .token(null)
                .build();
    }

    @Override
    public AuthResponse login(LoginRequest request) {

        // 1. Check if user exists in database
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        // 2. Check active flag
        if (user.getActive() == null || !user.getActive()) {
            throw new RuntimeException("Account is deactivated. Please contact support.");
        }

        // 3. Verify password
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        // 4. Generate JWT Token using the lowercase instance variable
        String token = jwtUtils.generateToken(user.getEmail(), user.getRole().name());

        // 5. Return token in response
        return AuthResponse.builder()
                .token(token)
                .message("Login successful")
                .build();
    }
}