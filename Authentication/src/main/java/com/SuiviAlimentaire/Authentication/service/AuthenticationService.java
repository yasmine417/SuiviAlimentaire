package com.SuiviAlimentaire.Authentication.service;


import com.SuiviAlimentaire.Authentication.dto.LoginRequest;
import com.SuiviAlimentaire.Authentication.dto.LoginResponse;
import com.SuiviAlimentaire.Authentication.dto.RegisterRequest;
import com.SuiviAlimentaire.Authentication.entities.User;
import com.SuiviAlimentaire.Authentication.repository.UserRepository;
import com.SuiviAlimentaire.Authentication.security.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    public LoginResponse authenticate(LoginRequest loginRequest) {
        User user = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException("Mot de passe incorrect");
        }

        String token = jwtUtils.generateJwtToken(user.getUsername());
        return new LoginResponse(token);
    }

    public String register(RegisterRequest registerRequest) {

        if (userRepository.findByUsername(registerRequest.getUsername()).isPresent()) {
            throw new RuntimeException("Username déjà utilisé");
        }

        if (userRepository.findByEmail(registerRequest.getEmail()).isPresent()) {
            throw new RuntimeException("Email déjà utilisé");
        }

        User user = new User(
                registerRequest.getUsername(),
                registerRequest.getEmail(),
                passwordEncoder.encode(registerRequest.getPassword())
        );
        userRepository.save(user);

        return "Utilisateur créé avec succès";
    }





}
