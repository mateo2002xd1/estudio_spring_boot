package com.proyecto.estudio_spring_boot.security;

import com.proyecto.estudio_spring_boot.entity.UsuarioEntity;
import com.proyecto.estudio_spring_boot.repository.UsuarioRepository;
import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import org.springframework.stereotype.Component;

import org.springframework.web.filter.OncePerRequestFilter;
import java.util.Collections;
import java.util.Optional;

@Component
public class JwtFilter extends OncePerRequestFilter{
    
    @Autowired
    private JwtService jwtService;
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        
        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            return;
        }
        
        String token = authHeader.substring(7);
        
        String usuario = jwtService.leerUsuario(token);
        
        Optional<UsuarioEntity> usuarioExiste = usuarioRepository.findById(Integer.valueOf(usuario));
        
        if(usuarioExiste.isPresent() && jwtService.validarJWT(token, usuario)){
            UsernamePasswordAuthenticationToken authAutorizado = new UsernamePasswordAuthenticationToken(
                    usuario,
                    null,
                    Collections.emptyList()
            );
            
            authAutorizado.setDetails(
                    new WebAuthenticationDetailsSource()
                            .buildDetails(request)
            );
            
            SecurityContextHolder.getContext().setAuthentication(authAutorizado);
        }
        
        filterChain.doFilter(request, response);
    }
    
}
