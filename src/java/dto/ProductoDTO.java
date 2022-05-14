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
public class ProductoDTO {
    
    
    private String titulo;
    private String descripcion;
    private String urlFoto;
    private String estado;
    private String categoria;

    public ProductoDTO() {
    }
    
    
    
    private Integer idPRODUCTO;

    public Integer getIdPRODUCTO() {
        return idPRODUCTO;
    }

    public void setIdPRODUCTO(Integer idPRODUCTO) {
        this.idPRODUCTO = idPRODUCTO;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    
    
    
    
    
    
    
    
}
