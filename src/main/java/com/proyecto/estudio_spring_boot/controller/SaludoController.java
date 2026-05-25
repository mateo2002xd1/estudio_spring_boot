package com.proyecto.estudio_spring_boot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
        
@RestController
public class SaludoController {
    
    @GetMapping("/saludos")
    public String holaMundo(){
        return "hola mundo";
    }
    
}
