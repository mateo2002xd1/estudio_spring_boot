package com.proyecto.estudio_spring_boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.estudio_spring_boot.entity.ProductoEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductoRepository extends JpaRepository<ProductoEntity, Integer>{

    public List<ProductoEntity> findByNombre(String nombre);
    
}
