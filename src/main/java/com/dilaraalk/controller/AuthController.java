package com.dilaraalk.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dilaraalk.dto.DtoLoginRequest;
import com.dilaraalk.dto.DtoUserRegisterRequest;
import com.dilaraalk.service.IAuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final IAuthService authService;
    
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody DtoUserRegisterRequest request) {
        authService.register(request);
        return ResponseEntity.ok("Kayıt başarılı");
    }
    
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody DtoLoginRequest request) {
        String token = authService.login(request);
        return ResponseEntity.ok(Map.of("token", token));
    }
}