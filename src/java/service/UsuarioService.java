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
import java.util.stream.Collectors;
import javax.persistence.Query;
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

    public List<Usuario> filtrarUsuario(String nombre, String apellidos, String minEdad, String maxEdad, String tipo_usuario) {        
        return this.userFC.filtrarUsuarios(nombre, apellidos, minEdad, maxEdad, tipo_usuario);
    }
    
    public List<Usuario> getUsuariosCompradores() {    
        return userFC.findAll().stream().filter(j -> j.getSubastaList1().size() > 0).collect(Collectors.toList());
    }

    public List<Usuario> filtrarCompradores(String nombre, String apellidos, String sexo, String email, String domicilio, String ciudad_residencia, String minEdad, String maxEdad, String tipo_usuario, String saldo) {
        List<Usuario> c = userFC.findAll().stream().filter(j -> j.getSubastaList1().size() > 0).collect(Collectors.toList()), res;
        int inMinEd = -1, inMaxEd = -1;
        
        double inSaldo;
        
        if(minEdad.compareTo("") != 0 && maxEdad.compareTo("") != 0){
            inMinEd = Integer.getInteger(minEdad);
            inMaxEd = Integer.getInteger(maxEdad);
            
        }
        if(saldo.compareTo("") != 0 ){
            inSaldo = Double.valueOf(saldo);
        }
        
        res = c.stream().filter(u -> (u.getNombre().compareTo(nombre)== 0 || nombre.compareTo("") == 0) && (u.getEpellidos().compareTo(apellidos)== 0 || apellidos.compareTo("") == 0) 
                && (u.getSexo().compareTo(sexo) == 0 || sexo.compareTo("Sexo") == 0) && (u.getEmail().compareTo(email)==0 || email.compareTo("") == 0) 
                && (u.getDomicilio().compareTo(domicilio)==0 || domicilio.compareTo("")==0) && (u.getCiudadResidencia().compareTo(ciudad_residencia)==0 || ciudad_residencia.compareTo("") == 0)
                && (u.getTipoUsuario().compareTo(tipo_usuario) == 0 || tipo_usuario.compareTo("Tipo") == 0) 
                ).collect(Collectors.toList());
        //&& (u.getSaldo().compareTo(inSaldo) == 0 || saldo.compareTo("") == 0)
        return res;
    }
    
    public List<Usuario> filtrarCompradores2(String nombre, String apellidos, String sexo, String email, String domicilio, String ciudad_residencia, String minEdad, String maxEdad, String tipo_usuario, String saldo) {
        List<Usuario> c = userFC.findAll().stream().filter(j -> j.getSubastaList1().size() > 0).collect(Collectors.toList()), res = new ArrayList<>();
        int inMinEd, inMaxEd;
        double inSaldo = 0;
        try{
            inMinEd = Integer.getInteger(minEdad);
            inMaxEd = Integer.getInteger(maxEdad);
            inSaldo = Double.valueOf(saldo);
        }catch(Exception e){
            inMinEd = -1;
            inMaxEd = -1;
        }
        
        
        
        if(saldo.compareTo("") != 0){
            for(Usuario u : c){
                boolean b = (u.getNombre().compareTo(nombre)== 0 || nombre.compareTo("") == 0) && (u.getEpellidos().compareTo(apellidos)== 0 || apellidos.compareTo("") == 0) 
                && (u.getSexo().compareTo(sexo) == 0 || sexo.compareTo("Sexo") == 0) && (u.getEmail().compareTo(email)==0 || email.compareTo("") == 0) 
                && (u.getDomicilio().compareTo(domicilio)==0 || domicilio.compareTo("")==0) && (u.getCiudadResidencia().compareTo(ciudad_residencia)==0 || ciudad_residencia.compareTo("") == 0)
                && ((inMinEd == -1 && inMaxEd == -1) || (inMinEd <= u.getEdad() && inMaxEd >= u.getEdad())) && (u.getTipoUsuario().compareTo(tipo_usuario) == 0 || tipo_usuario.compareTo("Tipo") == 0)  
                && (u.getSaldo() == inSaldo);
                if(b){
                    res.add(u);
                }else{
                    System.out.println("No");
                }
            }
        }else{
            for(Usuario u : c){
                boolean b = (u.getNombre().compareTo(nombre)== 0 || nombre.compareTo("") == 0) && (u.getEpellidos().compareTo(apellidos)== 0 || apellidos.compareTo("") == 0) 
                && (u.getSexo().compareTo(sexo) == 0 || sexo.compareTo("Sexo") == 0) && (u.getEmail().compareTo(email)==0 || email.compareTo("") == 0) 
                && (u.getDomicilio().compareTo(domicilio)==0 || domicilio.compareTo("")==0) && (u.getCiudadResidencia().compareTo(ciudad_residencia)==0 || ciudad_residencia.compareTo("") == 0)
                && ((inMinEd == -1 && inMaxEd == -1) || (inMinEd <= u.getEdad() && inMaxEd >= u.getEdad())) && (u.getTipoUsuario().compareTo(tipo_usuario) == 0 || tipo_usuario.compareTo("Tipo") == 0);
                
                if(b){
                    res.add(u);
                }
            }
        }
        return res;
    }
    
}


