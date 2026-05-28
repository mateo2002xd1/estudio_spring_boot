package com.proyecto.estudio_spring_boot.service;

import com.proyecto.estudio_spring_boot.dto.AuthProcesosDTO;
import com.proyecto.estudio_spring_boot.entity.UsuarioEntity;
import com.proyecto.estudio_spring_boot.exception.NoAutorizadoException;
import com.proyecto.estudio_spring_boot.exception.RegistroExisteException;
import com.proyecto.estudio_spring_boot.repository.UsuarioRepository;
import com.proyecto.estudio_spring_boot.security.JwtService;
import com.proyecto.estudio_spring_boot.security.PasswordService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UsuarioRepository usuariosDb;
    
    @Autowired
    private PasswordService encoder;
    
    @Autowired
    private JwtService jwtService;
    
    public String registrarUsuario(Integer id, AuthProcesosDTO datosUsuario){
        Optional<UsuarioEntity> usuarioExiste = usuariosDb.findById(id);
        
        if(usuarioExiste.isPresent()){
            UsuarioEntity usuarioConsultado = usuarioExiste.get();
            if(usuarioConsultado.getUsuario() == null){
                usuarioConsultado.setUsuario(id);
                usuarioConsultado.setPassword_hash(encoder.hashPassword(datosUsuario.getPassword()));
                
                usuariosDb.save(usuarioConsultado);
                
                return "Usuario registrado correctamente";
            }else{
                throw new RegistroExisteException("Usuario ya esta registrado");
            }
        }else{
            throw new RuntimeException("Usuario no existe");
        }
    }
    
    public String loginUsuario(Integer id, AuthProcesosDTO datosUsuario){
        Optional<UsuarioEntity> usuarioExiste = usuariosDb.findById(id);
        
        if(usuarioExiste.isPresent()){
            UsuarioEntity usuarioConsultado = usuarioExiste.get();
            if(usuarioConsultado.getUsuario() == null){
                throw new RuntimeException("Usuario no esta registrado");
            }else{
                if(encoder.matchPassword(usuarioConsultado.getPassword_hash(), datosUsuario.getPassword())){
                    return jwtService.generarJWT(Integer.toString(id));
                }else{
                    throw new NoAutorizadoException("Contraseña incorrecta");
                }
            }
        }else{
            throw new RuntimeException("Usuario no existe");
        }
    }
    
}
