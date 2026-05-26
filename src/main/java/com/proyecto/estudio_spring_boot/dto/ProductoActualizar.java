package com.proyecto.estudio_spring_boot.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoActualizar {
    @NotBlank(message = "Nombre del producto no puede ser vacio")
    private String nombre;
    
    @Positive(message = "Precio del producto debe ser mayor a 0")
    private Integer precio;
}