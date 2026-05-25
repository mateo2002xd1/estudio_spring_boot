package com.proyecto.estudio_spring_boot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ProductoResponse {
    private Integer codigo;
    private String nombre;
    private Integer precio;
}
