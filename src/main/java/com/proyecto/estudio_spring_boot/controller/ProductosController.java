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
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/productos")
public class ProductosController {
    @Autowired
    ProductoService productoService;
    
    @GetMapping("/{codigo}")
    public ProductoResponse buscarProductoUnico(
        @PathVariable Integer codigo
    ){
        return productoService.buscarProductoUnicoService(codigo);
    }
    
    @GetMapping
    public List<ProductoResponse> buscarProductoFiltros(
            @RequestParam(required = false) String nombre
    ){
        return productoService.buscarProductoFiltrosService(nombre);
    }
    
    @PostMapping
    public String insertarProducto(
            @RequestBody ProductoRequest producto
    ){
        return productoService.insertarProductoService(producto);
    }
    
    @PutMapping("/{codigo}")
    public String actualizarProducto(
            @PathVariable int codigo,
            @RequestBody ProductoActualizar producto
    ){
        return productoService.actualizarProductoService(codigo, producto);
    }
    
    @DeleteMapping("/{codigo}")
    public String eliminarProducto(
            @PathVariable int codigo
    ){
        return productoService.eliminarProductoService(codigo);
    }
}
