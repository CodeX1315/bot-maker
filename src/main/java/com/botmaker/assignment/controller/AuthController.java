package com.botmaker.assignment.controller;

import com.botmaker.assignment.dto.AuthResponse;
import com.botmaker.assignment.dto.LoginRequest;
import com.botmaker.assignment.dto.RegisterRequest;
import com.botmaker.assignment.dto.UserResponse;
import com.botmaker.assignment.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(authService.register(request));
    }
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request){
        return ResponseEntity.ok(authService.login(request));

    }

    @GetMapping("/admin/dashboard")
    public String adminDashboard(){
        return "Admin Access";
    }
    @GetMapping("/user/dashboard")
    public String userDashboard(){
        return "User Access";
    }

}
