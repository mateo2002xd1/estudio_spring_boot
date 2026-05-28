package com.proyecto.estudio_spring_boot.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordService {
    final private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    
    public String hashPassword(String password){
        return encoder.encode(password);
    }
    
    public boolean matchPassword(String passwordHash, String password){
        return encoder.matches(password, passwordHash);
    }
}
