/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entity.Subasta;
import entity.Usuario;

/**
 *
 * @author migue
 */
public class NotificacionesDTO {
    
    
    private Subasta idSubasta;
    private Usuario idUsuario;
    private Integer id;
    private String ganador;

    public NotificacionesDTO() {
    }
    
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGanador() {
        return ganador;
    }

    public void setGanador(String ganador) {
        this.ganador = ganador;
    }

    public Subasta getIdSubasta() {
        return idSubasta;
    }

    public void setIdSubasta(Subasta idSubasta) {
        this.idSubasta = idSubasta;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }
    
}
