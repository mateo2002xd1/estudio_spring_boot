package com.proyecto.estudio_spring_boot.service;
import com.proyecto.estudio_spring_boot.model.Usuario;
import com.proyecto.estudio_spring_boot.repository.UsuarioRepository;

public class UsuarioService {
    private UsuarioRepository usuariosDb = new UsuarioRepository();
    
    public void insertarUsuario(int id, String nombre, int edad){
        Usuario usuario = new Usuario(id, nombre, edad);
        if(usuariosDb.buscarUsuario(id, null, null).isEmpty()){
            usuariosDb.guardarUsuario(usuario);
            System.out.println("Se inserto correctamente el usuario " + usuario);
        }else{
            System.out.println("El usuario " + usuario + " ya existe");
        }
   }
    
   public void mostrarUsuarios(){
       System.out.println(usuariosDb.listarUsuarios());
   }
   
   public void buscarUsuariosFiltro(Integer id, String nombre, Integer edad){
       System.out.println("Usuarios filtrados " +usuariosDb.buscarUsuario(id, nombre, edad));
   }
   
   public void eliminarUsuario(Integer id){
       if(!usuariosDb.buscarUsuario(id, null, null).isEmpty()){
           Usuario usaurioEliminar = usuariosDb.buscarUsuario(id, null, null).getFirst();
           usuariosDb.eliminarUsuario(usaurioEliminar);
           System.out.println("Se elimino correctamente el usuario con ID " + id);   
       }else{
           System.out.println("El usuario no existe");   
       }
   }
   
}
