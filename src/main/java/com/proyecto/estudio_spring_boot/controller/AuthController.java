package com.proyecto.estudio_spring_boot.controller;

import com.proyecto.estudio_spring_boot.service.AuthService;

import com.proyecto.estudio_spring_boot.dto.AuthProcesosDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    
    @PostMapping("/registro/{id}")
    public ResponseEntity<String> authRegistroController(
            @PathVariable Integer id,
            @Valid @RequestBody AuthProcesosDTO datosRegistro
    ){
        return ResponseEntity.status(HttpStatus.OK).body(authService.registrarUsuario(id, datosRegistro));
    }
    
    @PostMapping("/login/{id}")
    public ResponseEntity<String> authLoginController(
            @PathVariable Integer id,
            @Valid @RequestBody AuthProcesosDTO datosLogin
    ){
        return ResponseEntity.status(HttpStatus.OK).body(authService.loginUsuario(id, datosLogin));
    }
    
}