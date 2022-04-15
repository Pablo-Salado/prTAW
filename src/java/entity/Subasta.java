/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "subasta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Subasta.findAll", query = "SELECT s FROM Subasta s")
    , @NamedQuery(name = "Subasta.findByIdSUBASTA", query = "SELECT s FROM Subasta s WHERE s.idSUBASTA = :idSUBASTA")
    , @NamedQuery(name = "Subasta.findByApertura", query = "SELECT s FROM Subasta s WHERE s.apertura = :apertura")
    , @NamedQuery(name = "Subasta.findByCierre", query = "SELECT s FROM Subasta s WHERE s.cierre = :cierre")
    , @NamedQuery(name = "Subasta.findByPujaMaxima", query = "SELECT s FROM Subasta s WHERE s.pujaMaxima = :pujaMaxima")
    , @NamedQuery(name = "Subasta.findByPrecioInicial", query = "SELECT s FROM Subasta s WHERE s.precioInicial = :precioInicial")})
public class Subasta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idSUBASTA")
    private Integer idSUBASTA;
    @Column(name = "APERTURA")
    @Temporal(TemporalType.DATE)
    private Date apertura;
    @Column(name = "CIERRE")
    @Temporal(TemporalType.DATE)
    private Date cierre;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "PUJA_MAXIMA")
    private Double pujaMaxima;
    @Column(name = "PRECIO_INICIAL")
    private Double precioInicial;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "subasta1")
    private List<Pujadores> pujadoresList;
    @JoinColumn(name = "PRODUCTO", referencedColumnName = "idPRODUCTO")
    @ManyToOne(optional = false)
    private Producto producto;
    @JoinColumn(name = "VENDEDOR", referencedColumnName = "idUSUARIO")
    @ManyToOne(optional = false)
    private Usuario vendedor;
    @JoinColumn(name = "COMPRADOR", referencedColumnName = "idUSUARIO")
    @ManyToOne
    private Usuario comprador;
    @OneToMany(mappedBy = "subasta")
    private List<Producto> productoList;

    public Subasta() {
    }

    public Subasta(Integer idSUBASTA) {
        this.idSUBASTA = idSUBASTA;
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

    @XmlTransient
    public List<Pujadores> getPujadoresList() {
        return pujadoresList;
    }

    public void setPujadoresList(List<Pujadores> pujadoresList) {
        this.pujadoresList = pujadoresList;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Usuario getVendedor() {
        return vendedor;
    }

    public void setVendedor(Usuario vendedor) {
        this.vendedor = vendedor;
    }

    public Usuario getComprador() {
        return comprador;
    }

    public void setComprador(Usuario comprador) {
        this.comprador = comprador;
    }

    @XmlTransient
    public List<Producto> getProductoList() {
        return productoList;
    }

    public void setProductoList(List<Producto> productoList) {
        this.productoList = productoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSUBASTA != null ? idSUBASTA.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Subasta)) {
            return false;
        }
        Subasta other = (Subasta) object;
        if ((this.idSUBASTA == null && other.idSUBASTA != null) || (this.idSUBASTA != null && !this.idSUBASTA.equals(other.idSUBASTA))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Subasta[ idSUBASTA=" + idSUBASTA + " ]";
    }
    
}
