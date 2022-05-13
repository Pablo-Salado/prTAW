/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;
import dao.SubastaFacade;
import dao.UsuarioFacade;
import entity.Producto;
import entity.Subasta;
import entity.Usuario;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.Query;
/**
 *
 * @author Gorpax
 */
@Stateless
public class UsuarioService {
    @EJB UsuarioFacade userFC;
    
    public Usuario buscarUsuario(Integer id) {
        return this.userFC.find(id);
    }

    public List<Usuario> listarUsuario() {
        return this.userFC.findAll();
    }
    
     public void rellenarUsuario(Usuario user,String nombre,String apellidos,String sexo, String password, String email, String domicilio, String ciudad, Integer edad,String tipo, Double saldo) {
        
        user.setNombre(nombre);
        user.setEpellidos(apellidos);
        user.setSexo(sexo);
        user.setPassword(password);
        user.setEmail(email);
        user.setDomicilio(domicilio);
        user.setCiudadResidencia(ciudad);
        user.setEdad(edad);
        user.setTipoUsuario(tipo);
        user.setSaldo(saldo);

    }
     
    public void crearUsuario(String nombre,String apellidos,String sexo, String password, String email, String domicilio, String ciudad, Integer edad,String tipo, Double saldo){
        
        Usuario user = new Usuario();
        
       this.rellenarUsuario(user, nombre, apellidos, sexo, password, email, domicilio, ciudad, edad, tipo, saldo);
        
        this.userFC.create(user);
    }
    
    public Usuario comprobarUser(String email, String pass){
        return this.userFC.comprobarUsuario(email, pass);
    }
    
    public void eliminarUsuario(Usuario user){

        this.userFC.remove(user);
    }
    
    public void eliminarUsuarioId(Integer id){
        Usuario usuario = this.userFC.find(id);

        this.userFC.remove(usuario);
    }
    
    public void modificarUsuario(Usuario user,String nombre,String apellidos,String sexo, String password, String email, String domicilio, String ciudad, Integer edad,String tipo, Double saldo){
       this.rellenarUsuario(user, nombre, apellidos, sexo, password, email, domicilio, ciudad, edad, tipo, saldo);
       
       this.userFC.edit(user);
    }
    
    public void modificarUsuarioId(Integer id,String nombre,String apellidos,String sexo, String password, String email, String domicilio, String ciudad, Integer edad,String tipo, Double saldo){
       Usuario user = this.userFC.find(id);
        
       this.rellenarUsuario(user, nombre, apellidos, sexo, password, email, domicilio, ciudad, edad, tipo, saldo);
       
       this.userFC.edit(user);
    }
    
    public void restaSaldo(Usuario user,Double saldo){
        user.setSaldo(saldo);
        
        this.userFC.edit(user);
    }

    public List<Usuario> filtrarUsuario(String nombre, String apellidos, String minEdad, String maxEdad, String tipo_usuario) {        
        return this.userFC.filtrarUsuarios(nombre, apellidos, minEdad, maxEdad, tipo_usuario);
    }
}
