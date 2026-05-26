package com.proyecto.estudio_spring_boot.exception;


public class RegistroExisteException extends RuntimeException{
    
    public RegistroExisteException(String mensaje){
        super(mensaje);
    }
    
}
