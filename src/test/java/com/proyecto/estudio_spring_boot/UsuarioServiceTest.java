package com.proyecto.estudio_spring_boot;

import com.proyecto.estudio_spring_boot.dto.UsuarioResponse;
import com.proyecto.estudio_spring_boot.entity.UsuarioEntity;
import com.proyecto.estudio_spring_boot.repository.UsuarioRepository;
import com.proyecto.estudio_spring_boot.service.UsuarioService;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {
    @Mock
    private UsuarioRepository usuarioRepository;
    
    @InjectMocks
    private UsuarioService usuarioService;
    
    @Test
    void contextLoads() {
        int suma = 2 + 2;


        System.out.println(suma);
        assertEquals(4, suma);

    }
    
    @Test
    void testbuscarUsuariosFiltro(){
        UsuarioEntity usuarioTest = new UsuarioEntity();
        
        usuarioTest.setId(1001);
        usuarioTest.setNombre("TEST");
        usuarioTest.setEdad(24);
        
        when(usuarioRepository.findById(1001)).thenReturn(Optional.of(usuarioTest));
        
        UsuarioResponse response = usuarioService.buscarUsuariosFiltro(1001);
        
        System.out.println(response);
        
        assertEquals(1001, response.getId());
    }
}
