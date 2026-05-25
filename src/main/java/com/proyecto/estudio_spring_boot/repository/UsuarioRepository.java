package com.proyecto.estudio_spring_boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.estudio_spring_boot.entity.UsuarioEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository
       extends JpaRepository<UsuarioEntity, Integer> {

    public Optional<UsuarioEntity> findByUsuario(Integer usuario);

    public List<UsuarioEntity> findByNombre(String nombre);
}