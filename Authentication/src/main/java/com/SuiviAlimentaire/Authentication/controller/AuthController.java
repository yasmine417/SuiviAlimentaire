package com.SuiviAlimentaire.Authentication.controller;



import com.SuiviAlimentaire.Authentication.dto.LoginRequest;
import com.SuiviAlimentaire.Authentication.dto.LoginResponse;
import com.SuiviAlimentaire.Authentication.dto.RegisterRequest;
import com.SuiviAlimentaire.Authentication.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor  // ✅ Utilisez RequiredArgsConstructor, pas AllArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;  // ✅ Ajoutez 'final'

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        System.out.println("🔵 Login reçu pour : " + request.getUsername());
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        System.out.println("🔵 Register reçu pour : " + request.getUsername());
        return ResponseEntity.ok(authenticationService.register(request));
    }
}