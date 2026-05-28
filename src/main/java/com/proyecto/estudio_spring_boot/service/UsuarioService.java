package com.proyecto.estudio_spring_boot.service;
import com.proyecto.estudio_spring_boot.dto.ActualizarUsuarioDto;
import com.proyecto.estudio_spring_boot.repository.UsuarioRepository;

import com.proyecto.estudio_spring_boot.dto.UsuarioRequest;
import com.proyecto.estudio_spring_boot.dto.UsuarioResponse;
import com.proyecto.estudio_spring_boot.entity.UsuarioEntity;
import com.proyecto.estudio_spring_boot.exception.RegistroExisteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    
   @Autowired
   private UsuarioRepository usuariosDb;
    
   public String insertarUsuario(UsuarioRequest request){

        Optional<UsuarioEntity> usuarioExistente =
                usuariosDb.findById(request.getId());

        if(usuarioExistente.isPresent()){
            throw new RegistroExisteException(
                     "Usuario " + Integer.toString(usuarioExistente.get().getId()) + " ya existe"
             );
        }

        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setId(request.getId());
        usuario.setNombre(request.getNombre());
        usuario.setEdad(request.getEdad());
        usuario.setUsuario(request.getUsuario());
        usuario.setPassword_hash(request.getPassword_hash());

        usuariosDb.save(usuario);

        return "Se insertó usuario";
    }
   
   public List<UsuarioResponse> mostrarUsuarios(String nombre){
       List<UsuarioEntity> usuariosConsutlados;
       if(nombre.isEmpty()){
           usuariosConsutlados = usuariosDb.findAll();
       }else{
           usuariosConsutlados = usuariosDb.findByNombre(nombre);
       }
       
       if(usuariosConsutlados.isEmpty()){
           throw new RuntimeException(
                "No se encontro usuario"
            );
       }
       
       List<UsuarioResponse> respuesta = new ArrayList<>();

        for(UsuarioEntity usuario : usuariosConsutlados){

            UsuarioResponse dto = new UsuarioResponse();

            dto.setId(usuario.getId());
            dto.setNombre(usuario.getNombre());
            dto.setEdad(usuario.getEdad());

            respuesta.add(dto);
        }

        return respuesta;
   }
   
   public UsuarioResponse buscarUsuariosFiltro(Integer id){
        Optional<UsuarioEntity> usuariosConsultado = usuariosDb.findById(id);
        
        if(usuariosConsultado.isPresent()){
            UsuarioResponse dto = new UsuarioResponse();

            dto.setId(usuariosConsultado.get().getId());
            dto.setNombre(usuariosConsultado.get().getNombre());
            dto.setEdad(usuariosConsultado.get().getEdad());

            return dto;
        }
        
        throw new RuntimeException(
                "No se encontro usuario"
            );
   }
   
   public String actualizarUsuario(Integer id, ActualizarUsuarioDto request) {

        Optional<UsuarioEntity> usuarioExistente =
                usuariosDb.findById(id);

        if(usuarioExistente.isEmpty()){
            throw new RuntimeException(
                "No se encontro usuario"
            );
        }

        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setId(id);
        usuario.setNombre(request.getNombre());
        usuario.setEdad(request.getEdad());

        usuariosDb.save(usuario);

        return "Se actualiazo el usuario";
    }
   
   public String eliminarUsuario(int id){
       Optional<UsuarioEntity> usuariosConsultado = usuariosDb.findById(id);
       
       if(usuariosConsultado.isPresent()){
           usuariosDb.delete(usuariosConsultado.get());
           return "Se elimino usuario";
       }else{
           throw new RuntimeException(
                "No se encontro usuario"
            );
       }
   }
   
}
