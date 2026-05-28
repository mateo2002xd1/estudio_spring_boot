package com.proyecto.estudio_spring_boot.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthProcesosDTO {
    @NotBlank(message = "Contraseña no puede ser vacio")
    private String password;
}
