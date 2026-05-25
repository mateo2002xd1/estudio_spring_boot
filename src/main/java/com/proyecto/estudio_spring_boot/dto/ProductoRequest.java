package com.proyecto.estudio_spring_boot.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoRequest {
    private Integer codigo;
    private String nombre;
    private Integer precio;
}
