package com.proyecto.estudio_spring_boot.controller;

import com.proyecto.estudio_spring_boot.dto.ProductoActualizar;
import com.proyecto.estudio_spring_boot.dto.ProductoRequest;
import com.proyecto.estudio_spring_boot.dto.ProductoResponse;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;

import com.proyecto.estudio_spring_boot.service.ProductoService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;



@RestController
@RequestMapping("/productos")
public class ProductosController {
    @Autowired
    private ProductoService productoService;
    
    @GetMapping("/{codigo}")
    public ResponseEntity<ProductoResponse> buscarProductoUnico(
        @PathVariable Integer codigo
    ){
        return ResponseEntity.status(HttpStatus.OK).body(productoService.buscarProductoUnicoService(codigo));
    }
    
    @GetMapping
    public ResponseEntity<List<ProductoResponse>> buscarProductoFiltros(
            @RequestParam(required = false) String nombre
    ){
        return ResponseEntity.status(HttpStatus.OK).body(productoService.buscarProductoFiltrosService(nombre));
    }
    
    @PostMapping
    public ResponseEntity<String> insertarProducto(
            @Valid @RequestBody ProductoRequest producto
    ){
        return ResponseEntity.status(HttpStatus.CREATED).body(productoService.insertarProductoService(producto));
    }
    
    @PutMapping("/{codigo}")
    public ResponseEntity<String> actualizarProducto(
            @PathVariable int codigo,
            @Valid  @RequestBody ProductoActualizar producto
    ){
        return ResponseEntity.status(HttpStatus.OK).body(productoService.actualizarProductoService(codigo, producto));
    }
    
    @DeleteMapping("/{codigo}")
    public ResponseEntity<String> eliminarProducto(
            @PathVariable int codigo
    ){
        return ResponseEntity.status(HttpStatus.OK).body(productoService.eliminarProductoService(codigo));
    }
}
