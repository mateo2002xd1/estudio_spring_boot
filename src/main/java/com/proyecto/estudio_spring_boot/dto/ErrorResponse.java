package com.proyecto.estudio_spring_boot.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@Builder
public class ErrorResponse {
    private LocalDateTime timestamp;

    private Integer status;

    private String error;

    private String mensaje;

    private Map<String, String> detalles;
}