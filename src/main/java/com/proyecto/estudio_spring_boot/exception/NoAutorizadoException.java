package com.proyecto.estudio_spring_boot.exception;


public class NoAutorizadoException extends RuntimeException{
    
    public NoAutorizadoException(String mensaje){
        super(mensaje);
    }
    
}
