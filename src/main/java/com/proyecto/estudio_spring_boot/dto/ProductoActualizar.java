package com.proyecto.estudio_spring_boot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoActualizar {
    private String nombre;
    private Integer precio;
}