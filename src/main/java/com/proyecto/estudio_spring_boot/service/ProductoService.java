package com.proyecto.estudio_spring_boot.service;
import com.proyecto.estudio_spring_boot.dto.ProductoActualizar;
import com.proyecto.estudio_spring_boot.repository.ProductoRepository;

import com.proyecto.estudio_spring_boot.dto.ProductoRequest;
import com.proyecto.estudio_spring_boot.dto.ProductoResponse;
import com.proyecto.estudio_spring_boot.entity.ProductoEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class ProductoService{
    @Autowired
    private ProductoRepository productoDb;
    
    public ProductoResponse buscarProductoUnicoService(Integer codigo){
        Optional<ProductoEntity> productoExiste = productoDb.findById(codigo);
        
        if(productoExiste.isPresent()){
            ProductoResponse response = new ProductoResponse();
            
            response.setCodigo(productoExiste.get().getCodigo());
            response.setNombre(productoExiste.get().getNombre());
            response.setPrecio(productoExiste.get().getPrecio());
            
            return response;
        }
        return null;
    }
    
    public List<ProductoResponse> buscarProductoFiltrosService(String nombre){
        List<ProductoEntity> productosFiltrados;
        if(nombre.isEmpty()){
            productosFiltrados = productoDb.findAll();
        }else{
            productosFiltrados = productoDb.findByNombre(nombre);
        }
        
        List<ProductoResponse> response = new ArrayList<>();
        
        
        for(ProductoEntity producto: productosFiltrados){
            ProductoResponse productoAgregar = new ProductoResponse();
            
            productoAgregar.setCodigo(producto.getCodigo());
            productoAgregar.setNombre(producto.getNombre());
            productoAgregar.setPrecio(producto.getPrecio());
            
            response.add(productoAgregar);
        }
        
        return response;
    }
    
    public String insertarProductoService(ProductoRequest producto){
        Optional<ProductoEntity> productoExiste = productoDb.findById(producto.getCodigo());
     
        if(productoExiste.isPresent()){
            return "Producto existe";
        }else{
            ProductoEntity productoInsertar = new ProductoEntity();
            
            productoInsertar.setCodigo(producto.getCodigo());
            productoInsertar.setNombre(producto.getNombre());
            productoInsertar.setPrecio(producto.getPrecio());
            
            productoDb.save(productoInsertar);
            
            return "Producto insertado";
        }
    }
    
    public String actualizarProductoService(Integer codigo, ProductoActualizar producto){
        Optional<ProductoEntity> productoExiste = productoDb.findById(codigo);
        
        if(productoExiste.isPresent()){
           ProductoEntity productoActualizar = new ProductoEntity();
           
           productoActualizar.setCodigo(productoExiste.get().getCodigo());
           productoActualizar.setNombre(producto.getNombre());
           productoActualizar.setPrecio(producto.getPrecio());
           
           productoDb.save(productoActualizar);
           
           return "Producto " + productoExiste.get().getCodigo() + " actualizado";
        }else{
            return "Producto no existe";
        }
    }
    
    public String eliminarProductoService(Integer codigo){
        Optional<ProductoEntity> productoExiste = productoDb.findById(codigo);
        
        if(productoExiste.isPresent()){
            productoDb.delete(productoExiste.get());
            
            return "Producto eliminado";
        }else{
            return "Producto no existe";   
        }
    }
}