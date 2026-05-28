package com.proyecto.estudio_spring_boot.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;

import io.jsonwebtoken.security.Keys;

import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;

@Service
public class JwtService {
    
    @Value("${jwt.secret}")
    private String mi_clave_super_protegida;
    
    @Value("${jwt.expiration}")
    private long expiracionToken;
    
    public String generarJWT(String usuario){
        return Jwts.builder().
                subject(usuario).
                issuedAt(new Date()).
                expiration(new Date(System.currentTimeMillis() + expiracionToken)).
                signWith(generarKey()).
                compact();
        
    }
    
    public boolean validarJWT(String token, String usuario){
        String usuarioToken = leerUsuario(token);
        
        return usuarioToken.equals(usuario) && !tokenExpirado(token);
    }
    
    public boolean tokenExpirado(String token){
        Claims claims = leerClaims(token);
        
        return claims.getExpiration().before(new Date());
    }
    
    public String leerUsuario(String token){
        Claims claims = leerClaims(token);
        return claims.getSubject();
    }
    
    public Claims leerClaims(String token){
                return Jwts.parser()
                .verifyWith(generarKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
    
    public SecretKey generarKey(){
        return Keys.hmacShaKeyFor(mi_clave_super_protegida.getBytes());
    }
}
