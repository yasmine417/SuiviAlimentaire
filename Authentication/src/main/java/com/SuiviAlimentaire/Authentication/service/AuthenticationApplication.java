package com.SuiviAlimentaire.Authentication.service;


import com.SuiviAlimentaire.Authentication.entities.User;
import com.SuiviAlimentaire.Authentication.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class AuthenticationApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthenticationApplication.class, args);
    }

    // ✅ Bean pour créer un utilisateur test au démarrage

}