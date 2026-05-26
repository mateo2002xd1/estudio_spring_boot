package com.proyecto.estudio_spring_boot.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActualizarUsuarioDto {
    @NotBlank(message = "Nombre del usuario no puede ser vacio")
    private String nombre;
    
    @Positive(message = "Edad del usuario debe ser mayor a 0")
    private Integer edad;
}