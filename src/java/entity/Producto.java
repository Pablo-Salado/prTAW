/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author X430F
 */
@Entity
@Table(name = "producto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Producto.findAll", query = "SELECT p FROM Producto p")
    , @NamedQuery(name = "Producto.findByIdPRODUCTO", query = "SELECT p FROM Producto p WHERE p.idPRODUCTO = :idPRODUCTO")
    , @NamedQuery(name = "Producto.findByTitulo", query = "SELECT p FROM Producto p WHERE p.titulo = :titulo")
    , @NamedQuery(name = "Producto.findByDescripcion", query = "SELECT p FROM Producto p WHERE p.descripcion = :descripcion")
    , @NamedQuery(name = "Producto.findByUrlFoto", query = "SELECT p FROM Producto p WHERE p.urlFoto = :urlFoto")
    , @NamedQuery(name = "Producto.findByEstado", query = "SELECT p FROM Producto p WHERE p.estado = :estado")
    , @NamedQuery(name = "Producto.findByCategoria", query = "SELECT p FROM Producto p WHERE p.categoria = :categoria")})
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPRODUCTO")
    private Integer idPRODUCTO;
    @Size(max = 45)
    @Column(name = "TITULO")
    private String titulo;
    @Size(max = 45)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Size(max = 45)
    @Column(name = "URL_FOTO")
    private String urlFoto;
    @Size(max = 45)
    @Column(name = "ESTADO")
    private String estado;
    @Size(max = 16)
    @Column(name = "CATEGORIA")
    private String categoria;
    @JoinTable(name = "favoritos", joinColumns = {
        @JoinColumn(name = "PRODUCTO", referencedColumnName = "idPRODUCTO")}, inverseJoinColumns = {
        @JoinColumn(name = "USUARIO", referencedColumnName = "idUSUARIO")})
    @ManyToMany
    private List<Usuario> usuarioList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "producto")
    private List<Subasta> subastaList;
    @JoinColumn(name = "SUBASTA", referencedColumnName = "idSUBASTA")
    @ManyToOne(optional = false)
    private Subasta subasta;

    public Producto() {
    }

    public Producto(Integer idPRODUCTO) {
        this.idPRODUCTO = idPRODUCTO;
    }

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

    @XmlTransient
    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    @XmlTransient
    public List<Subasta> getSubastaList() {
        return subastaList;
    }

    public void setSubastaList(List<Subasta> subastaList) {
        this.subastaList = subastaList;
    }

    public Subasta getSubasta() {
        return subasta;
    }

    public void setSubasta(Subasta subasta) {
        this.subasta = subasta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPRODUCTO != null ? idPRODUCTO.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Producto)) {
            return false;
        }
        Producto other = (Producto) object;
        if ((this.idPRODUCTO == null && other.idPRODUCTO != null) || (this.idPRODUCTO != null && !this.idPRODUCTO.equals(other.idPRODUCTO))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Producto[ idPRODUCTO=" + idPRODUCTO + " ]";
    }
    
}
