package com.proyecto.estudio_spring_boot.exception;

import com.proyecto.estudio_spring_boot.dto.ErrorResponse;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> manejarValid (MethodArgumentNotValidException ex){
        
        Map<String, String> detalles = new HashMap<>();
        
        ex.getBindingResult().getFieldErrors().forEach(
                e -> {
                    detalles.put(e.getField(), e.getDefaultMessage());
                }
        );
        
        ErrorResponse response = ErrorResponse.builder()
                                              .timestamp(LocalDateTime.now())
                                              .status(400)
                                              .error("Bad Request")
                                              .mensaje("Error de validacion")
                                              .detalles(detalles)
                                              .build();
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
    
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> manejoRuntimeException(RuntimeException ex){
        Map<String, String> detalles = new HashMap<>();
        
        detalles.put("error", ex.getMessage());
        
        ErrorResponse response = ErrorResponse.builder().timestamp(LocalDateTime.now()).status(404).error("Not Found").mensaje("No se encontro registro").detalles(detalles).build();
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        
    }
    
    @ExceptionHandler(RegistroExisteException.class)
    public ResponseEntity<ErrorResponse> manejoRegistroExisteException(RegistroExisteException ex){
        Map<String, String> detalles = new HashMap<>();
        
        detalles.put("error", ex.getMessage());
                
        ErrorResponse response = ErrorResponse.builder().timestamp(LocalDateTime.now()).status(409).error("Conflict").mensaje("Registro ya existe").detalles(detalles).build();
        
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }
    
    @ExceptionHandler(NoAutorizadoException.class)
    public ResponseEntity<ErrorResponse> manejoNoAutorizadoException(NoAutorizadoException ex){
        Map<String, String> detalles = new HashMap<>();
        
        detalles.put("error", ex.getMessage());
        
        ErrorResponse response = ErrorResponse.builder().timestamp(LocalDateTime.now()).status(401).error("No Autorizado").detalles(detalles).build();
        
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }
}
