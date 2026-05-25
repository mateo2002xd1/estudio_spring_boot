package com.proyecto.estudio_spring_boot.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActualizarUsuarioDto {
    private String nombre;
    private Integer edad;
}