package com.proyecto.estudio_spring_boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.proyecto.estudio_spring_boot.model.Usuario;
import com.proyecto.estudio_spring_boot.repository.UsuarioRepository;
import com.proyecto.estudio_spring_boot.service.UsuarioService;

@SpringBootApplication
public class EstudioSpringBootApplication {
        
        public static UsuarioService usuarioService = new UsuarioService();

        public static void main(String[] args) {
                
            usuarioService.insertarUsuario(1001577454, "MATEO", 24);
            usuarioService.insertarUsuario(1001577452, "PELAEZ", 1);
            usuarioService.insertarUsuario(1001577453, "MATEO", 13);
            usuarioService.insertarUsuario(1001577453, "MATEO", 13);
            usuarioService.mostrarUsuarios();
            usuarioService.buscarUsuariosFiltro(null, "MATEO", null);
            usuarioService.eliminarUsuario(1001577454);
            usuarioService.mostrarUsuarios();
            usuarioService.eliminarUsuario(1001577455);
            usuarioService.mostrarUsuarios();
            //SpringApplication.run(EstudioSpringBootApplication.class, args);
	}
        
}