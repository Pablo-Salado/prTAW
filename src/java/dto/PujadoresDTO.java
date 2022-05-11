/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entity.Subasta;
import entity.Usuario;
import java.util.Date;

/**
 *
 * @author migue
 */
public class PujadoresDTO {

    
    private Double valorPuja;
    private Date fecha;
    private Subasta subasta;
    private Usuario usuario;
    private Integer iDPuja;
    
   public PujadoresDTO() {
    }
    
    public Integer getiDPuja() {
        return iDPuja;
    }

    public void setiDPuja(Integer iDPuja) {
        this.iDPuja = iDPuja;
    }

    public Double getValorPuja() {
        return valorPuja;
    }

    public void setValorPuja(Double valorPuja) {
        this.valorPuja = valorPuja;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Subasta getSubasta() {
        return subasta;
    }

    public void setSubasta(Subasta subasta) {
        this.subasta = subasta;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    
}
