package com.proyecto.estudio_spring_boot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import com.proyecto.estudio_spring_boot.dto.UsuarioRequest;
import com.proyecto.estudio_spring_boot.dto.UsuarioResponse;
import com.proyecto.estudio_spring_boot.dto.ActualizarUsuarioDto;
import com.proyecto.estudio_spring_boot.service.UsuarioService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/usuarios")
public class UsuariosController {
    @Autowired
    private UsuarioService usuarioService;
    
    @PostMapping
    public ResponseEntity<String> insertarUsuario(
            @Valid @RequestBody UsuarioRequest usuario
    ){
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.insertarUsuario(usuario));
    }
    
    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> consultarUsuario(
            @RequestParam(required = false) String nombre
    ){
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.mostrarUsuarios(nombre));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> consultarUsuarioUnico(
            @PathVariable int id
    ){
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.buscarUsuariosFiltro(id));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarUsuario(
            @PathVariable Integer id,
            @Valid  @RequestBody ActualizarUsuarioDto datosUsuario
    ){
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.actualizarUsuario(id, datosUsuario));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarUsuario(
            @PathVariable int id
    ){
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.eliminarUsuario(id));   
    }
}
