package com.proyecto.estudio_spring_boot.repository;

import com.proyecto.estudio_spring_boot.model.Usuario;
import java.util.ArrayList;
import java.util.List;


public class UsuarioRepository {
    private List<Usuario> usuariosDB = new ArrayList<>();
    
    public void guardarUsuario(Usuario usuarioInsertar){
        usuariosDB.add(usuarioInsertar);
    }
    
    public List<Usuario> listarUsuarios(){
        return usuariosDB;
    }
    
    public List<Usuario> buscarUsuario(Integer id, String nombre, Integer edad){
        List<Usuario> usuariosFiltrados = usuariosDB.stream().filter(usuario -> (id == null || usuario.getId() == id ) && (nombre == null || usuario.getNombre().toUpperCase().equals(nombre.toUpperCase())) && (edad == null || usuario.getEdad() == edad)).toList();
        return usuariosFiltrados;
    }
    
    public void eliminarUsuario(Usuario usuarioEliminar){
        usuariosDB.remove(usuarioEliminar);
        
    }
}
