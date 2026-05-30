package com.proyecto.estudio_spring_boot;

import com.proyecto.estudio_spring_boot.controller.UsuariosController;
import com.proyecto.estudio_spring_boot.service.UsuarioService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(UsuariosController.class)
public class UsuarioControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UsuarioService usuarioService;
    
    @Test
    void usuarioLoginTest() throws Exception {
        
        
        mockMvc.perform(
                get("/usuarios?nombre=")
        );
        
        
    }
}
