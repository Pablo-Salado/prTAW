/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;


import java.util.Date;

/**
 *
 * @author Miguel Angel Cosano Ramirez
 */
public class PujadoresDTO {

    
    private Double valorPuja;
    private Date fecha;
    private SubastaDTO subasta;
    private UsuarioDTO usuario;
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

    public SubastaDTO getSubasta() {
        return subasta;
    }

    public void setSubasta(SubastaDTO subasta) {
        this.subasta = subasta;
    }

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }
    
    
}
