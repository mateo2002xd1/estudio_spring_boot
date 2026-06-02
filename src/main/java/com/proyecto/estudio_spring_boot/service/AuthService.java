package com.proyecto.estudio_spring_boot.service;

import com.proyecto.estudio_spring_boot.dto.AuthProcesosDTO;
import com.proyecto.estudio_spring_boot.entity.UsuarioEntity;
import com.proyecto.estudio_spring_boot.exception.NoAutorizadoException;
import com.proyecto.estudio_spring_boot.exception.RegistroExisteException;
import com.proyecto.estudio_spring_boot.repository.UsuarioRepository;
import com.proyecto.estudio_spring_boot.security.JwtService;
import com.proyecto.estudio_spring_boot.security.PasswordService;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Slf4j
@Service
public class AuthService {
    @Autowired
    private UsuarioRepository usuariosDb;
    
    @Autowired
    private PasswordService encoder;
    
    @Autowired
    private JwtService jwtService;
    
    private RestClient restClient = RestClient.create();
    
    public String registrarUsuario(Integer id, AuthProcesosDTO datosUsuario){
        Optional<UsuarioEntity> usuarioExiste = usuariosDb.findById(id);
        
        if(usuarioExiste.isPresent()){
            UsuarioEntity usuarioConsultado = usuarioExiste.get();
            if(usuarioConsultado.getUsuario() == null){
                usuarioConsultado.setUsuario(id);
                usuarioConsultado.setPassword_hash(encoder.hashPassword(datosUsuario.getPassword()));
                
                usuariosDb.save(usuarioConsultado);
                log.info("Usuario registrado correctamente");
                return "Usuario registrado correctamente";
            }else{
                log.warn("Usuario ya esta registrado");
                throw new RegistroExisteException("Usuario ya esta registrado");
            }
        }else{
            log.warn("Usuario no existe");
            throw new RuntimeException("Usuario no existe");
        }
    }
    
    public String loginUsuario(Integer id, AuthProcesosDTO datosUsuario){
        Optional<UsuarioEntity> usuarioExiste = usuariosDb.findById(id);
        
        if(usuarioExiste.isPresent()){
            UsuarioEntity usuarioConsultado = usuarioExiste.get();
            if(usuarioConsultado.getUsuario() == null){
                log.warn("Usuario no esta registrado");
                throw new RuntimeException("Usuario no esta registrado");
            }else{
                if(encoder.matchPassword(usuarioConsultado.getPassword_hash(), datosUsuario.getPassword())){
                    String token = jwtService.generarJWT(Integer.toString(id));
                    log.info("token generado ");
                    return token;
                }else{
                    log.warn("Contraseña incorrecta");
                    throw new NoAutorizadoException("Contraseña incorrecta");
                }
            }
        }else{
            log.warn("Usuario no existe");
            throw new RuntimeException("Usuario no existe");
        }
        
        /*return restClient.post()
                .uri("https://estudio-spring-boot.onrender.com/auth/login/" + Integer.toString(id))
                .body(datosUsuario)
                .retrieve()
                .body(String.class);*/
    }
    
}
