/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;



import java.util.Date;
import java.util.List;

/**
 *
 * @author Miguel Angel Cosano Ramirez
 */
public class SubastaDTO {
     private Date apertura;
    private Date cierre;
    private Double pujaMaxima;
    private Double precioInicial;
    private ProductoDTO producto;
    private UsuarioDTO vendedor;
    private UsuarioDTO comprador;
    private List<PujadoresDTO> pujadoresList;
    private Integer idSUBASTA;
    
    public SubastaDTO(){
        
    }

    public Integer getIdSUBASTA() {
        return idSUBASTA;
    }

    public void setIdSUBASTA(Integer idSUBASTA) {
        this.idSUBASTA = idSUBASTA;
    }

    public Date getApertura() {
        return apertura;
    }

    public void setApertura(Date apertura) {
        this.apertura = apertura;
    }

    public Date getCierre() {
        return cierre;
    }

    public void setCierre(Date cierre) {
        this.cierre = cierre;
    }

    public Double getPujaMaxima() {
        return pujaMaxima;
    }

    public void setPujaMaxima(Double pujaMaxima) {
        this.pujaMaxima = pujaMaxima;
    }

    public Double getPrecioInicial() {
        return precioInicial;
    }

    public void setPrecioInicial(Double precioInicial) {
        this.precioInicial = precioInicial;
    }

    public ProductoDTO getProducto() {
        return producto;
    }

    public void setProducto(ProductoDTO producto) {
        this.producto = producto;
    }

    public UsuarioDTO getVendedor() {
        return vendedor;
    }

    public void setVendedor(UsuarioDTO vendedor) {
        this.vendedor = vendedor;
    }

    public UsuarioDTO getComprador() {
        return comprador;
    }

    public void setComprador(UsuarioDTO comprador) {
        this.comprador = comprador;
    }

    public List<PujadoresDTO> getPujadoresList() {
        return pujadoresList;
    }

    public void setPujadoresList(List<PujadoresDTO> pujadoresList) {
        this.pujadoresList = pujadoresList;
    }
   
    
}
