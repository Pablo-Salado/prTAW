/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;



/**
 *
 * @author Miguel Angel Cosano Ramirez
 */
public class NotificacionesDTO {
    
    
    private SubastaDTO idSubasta;
    private UsuarioDTO idUsuario;
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

    public SubastaDTO getIdSubasta() {
        return idSubasta;
    }

    public void setIdSubasta(SubastaDTO idSubasta) {
        this.idSubasta = idSubasta;
    }

    public UsuarioDTO getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(UsuarioDTO idUsuario) {
        this.idUsuario = idUsuario;
    }
    
}
