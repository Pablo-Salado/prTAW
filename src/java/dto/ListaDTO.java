/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entity.Usuario;

/**
 *
 * @author Javier Santiburcio
 */
public class ListaDTO {
    
    private Integer idLista;
    private String nombre;
    private String descripcion;
    private String atributos;
    private Usuario usuario; 
    
    public ListaDTO(){
        
    }
    
    public Integer getIdLISTA() {
        return idLista;
    }

    public void setIdLISTA(Integer idLISTA) {
        this.idLista = idLISTA;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getAtributos() {
        return atributos;
    }

    public void setAtributos(String atributos) {
        this.atributos = atributos;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    
    
}
