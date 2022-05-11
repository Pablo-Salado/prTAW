/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import dto.PujadoresDTO;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Gorpax
 */
@Entity
@Table(name = "pujadores")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pujadores.findAll", query = "SELECT p FROM Pujadores p")
    , @NamedQuery(name = "Pujadores.findByIDPuja", query = "SELECT p FROM Pujadores p WHERE p.iDPuja = :iDPuja")
    , @NamedQuery(name = "Pujadores.findByValorPuja", query = "SELECT p FROM Pujadores p WHERE p.valorPuja = :valorPuja")
    , @NamedQuery(name = "Pujadores.findByFecha", query = "SELECT p FROM Pujadores p WHERE p.fecha = :fecha")})
public class Pujadores implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDPuja")
    private Integer iDPuja;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "VALOR_PUJA")
    private Double valorPuja;
    @Column(name = "FECHA")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @JoinColumn(name = "SUBASTA", referencedColumnName = "idSUBASTA")
    @ManyToOne(optional = false)
    private Subasta subasta;
    @JoinColumn(name = "USUARIO", referencedColumnName = "idUSUARIO")
    @ManyToOne(optional = false)
    private Usuario usuario;

    public Pujadores() {
    }

    public Pujadores(Integer iDPuja) {
        this.iDPuja = iDPuja;
    }

    public Integer getIDPuja() {
        return iDPuja;
    }

    public void setIDPuja(Integer iDPuja) {
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDPuja != null ? iDPuja.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pujadores)) {
            return false;
        }
        Pujadores other = (Pujadores) object;
        if ((this.iDPuja == null && other.iDPuja != null) || (this.iDPuja != null && !this.iDPuja.equals(other.iDPuja))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Pujadores[ iDPuja=" + iDPuja + " ]";
    }
    
    public PujadoresDTO toDTO(){
        PujadoresDTO res = new PujadoresDTO();
        res.setFecha(fecha);
        res.setSubasta(subasta);
        res.setUsuario(usuario);
        res.setValorPuja(valorPuja);
        res.setiDPuja(iDPuja);
        return res;
    }
    
}
