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
import dto.UsuarioDTO;
import entity.Producto;
import entity.Subasta;
import entity.Usuario;
import java.util.ArrayList;
import java.util.Date;
/**
 *
 * @author Gorpax
 */
@Stateless
public class UsuarioService {
    @EJB UsuarioFacade userFC;
    
    private List<UsuarioDTO> listaEntityADTO (List<Usuario> lista) {
        List<UsuarioDTO> listaDTO = null;
        if (lista != null) {
            listaDTO = new ArrayList<>();
            for (Usuario dc:lista) {
                listaDTO.add(dc.toDTO());
            }
        }
        return listaDTO;
    }
    
    public UsuarioDTO buscarUsuario(Integer id) {
        return this.userFC.find(id).toDTO();
    }

    public List<UsuarioDTO> listarUsuario() {
        List<Usuario> list =this.userFC.findAll();
        return listaEntityADTO(list);
    }
    
     public void rellenarUsuario(Integer idUsuario,String nombre,String apellidos,String sexo, String password, String email, String domicilio, String ciudad, Integer edad,String tipo, Double saldo) {
        
        Usuario user = this.userFC.find(idUsuario);
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
     
    public void crearUsuario(String nombre,String apellidos,String sexo, String password, String email, String domicilio, String ciudad, Integer edad, Double saldo){
        
        Usuario user = new Usuario();
        
        user.setNombre(nombre);
        user.setEpellidos(apellidos);
        user.setSexo(sexo);
        user.setPassword(password);
        user.setEmail(email);
        user.setDomicilio(domicilio);
        user.setCiudadResidencia(ciudad);
        user.setEdad(edad);
        user.setTipoUsuario("CV");
        user.setSaldo(saldo);
        
       
        
        this.userFC.create(user);
    }
    
    public UsuarioDTO comprobarUser(String email, String pass){
        return this.userFC.comprobarUsuario(email, pass).toDTO();
    }
    
    public void eliminarUsuario(Integer idUser){
        
        Usuario user = this.userFC.find(idUser);
        this.userFC.remove(user);
    }
    
    public void eliminarUsuarioId(Integer id){
        Usuario usuario = this.userFC.find(id);

        this.userFC.remove(usuario);
    }
    
    public void modificarUsuario(Integer idUser,String nombre,String apellidos,String sexo, String password, String email, String domicilio, String ciudad, Integer edad,String tipo, Double saldo){
       
        this.rellenarUsuario(idUser, nombre, apellidos, sexo, password, email, domicilio, ciudad, edad, tipo, saldo);
       
        this.userFC.edit(this.userFC.find(idUser));
    }
    
    public void modificarUsuarioId(Integer idUser,String nombre,String apellidos,String sexo, String password, String email, String domicilio, String ciudad, Integer edad,String tipo, Double saldo){
       Usuario user = this.userFC.find(idUser);
        
       this.rellenarUsuario(idUser, nombre, apellidos, sexo, password, email, domicilio, ciudad, edad, tipo, saldo);
       
       this.userFC.edit(user);
    }
    
    public void restaSaldo(Integer userId,Double saldo){
        Usuario user = this.userFC.find(userId);
        user.setSaldo(saldo);
        
        this.userFC.edit(user);
    }
}
