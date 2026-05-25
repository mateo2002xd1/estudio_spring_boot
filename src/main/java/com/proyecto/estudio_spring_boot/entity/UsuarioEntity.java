package com.proyecto.estudio_spring_boot.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "usuarios")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioEntity {
    @Id
    private Integer id;
    
    @Column(name = "nombre")
    private String nombre;
    
    @Column(name = "edad")
    private Integer edad;
    
    @Column(name = "usuario")
    private Integer usuario;
    
    @Column(name = "password_hash")
    private String password_hash;
}