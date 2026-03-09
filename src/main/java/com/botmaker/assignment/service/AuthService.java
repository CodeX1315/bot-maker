package com.botmaker.assignment.service;

import com.botmaker.assignment.dto.AuthResponse;
import com.botmaker.assignment.dto.LoginRequest;
import com.botmaker.assignment.dto.RegisterRequest;
import com.botmaker.assignment.dto.UserResponse;
import com.botmaker.assignment.entity.Role;
import com.botmaker.assignment.entity.UserEntity;
import com.botmaker.assignment.mapper.UserMapper;
import com.botmaker.assignment.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public UserResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already registered");
        }

        UserEntity user = userMapper.toEntity(request);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        if(request.getRole() != null) {
            user.setRole(request.getRole());
        } else {
            user.setRole(Role.USER);
        }

        UserEntity savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        UserEntity userEntity = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        String token = jwtService.generateToken(userEntity);
        return new AuthResponse(token);
    }
}