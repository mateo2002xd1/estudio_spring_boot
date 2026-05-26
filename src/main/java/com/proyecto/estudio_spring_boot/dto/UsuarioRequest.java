package com.proyecto.estudio_spring_boot.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRequest {
    
    @Positive(message = "ID de usuario debe ser mayor a 0")
    private Integer id;
    
    @NotBlank(message = "Nombre del usuario no puede ser vacio")
    private String nombre;
    
    @Positive(message = "Edad del usuario debe ser mayor a 0")
    private Integer edad;
    
    private Integer usuario;
    private String password_hash;
}