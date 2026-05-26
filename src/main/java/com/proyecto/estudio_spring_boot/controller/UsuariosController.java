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
        String response = usuarioService.insertarUsuario(usuario);
        return ResponseEntity.status(200).body(response);
    }
    
    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> consultarUsuario(
            @RequestParam(required = false) String nombre
    ){
        List<UsuarioResponse> usuariosFiltrados = usuarioService.mostrarUsuarios(nombre);
        return ResponseEntity.status(200).body(usuariosFiltrados);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> consultarUsuarioUnico(
            @PathVariable int id
    ){
        UsuarioResponse usuariosFiltrados = usuarioService.buscarUsuariosFiltro(id);
        return ResponseEntity.status(200).body(usuariosFiltrados);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarUsuario(
            @PathVariable Integer id,
            @Valid  @RequestBody ActualizarUsuarioDto datosUsuario
    ){
        String response = usuarioService.actualizarUsuario(id, datosUsuario);
        return ResponseEntity.status(200).body(response);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarUsuario(
            @PathVariable int id
    ){
        String response = usuarioService.eliminarUsuario(id);
        return ResponseEntity.status(200).body(response);   
    }
    
    @GetMapping("/test")
    public ResponseEntity<String> testEndPoint(){
        String dbUrl = System.getenv("DB_URL");
        return ResponseEntity.status(200).body(dbUrl);
    }
}
