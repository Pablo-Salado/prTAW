/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "pujadores")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pujadores.findAll", query = "SELECT p FROM Pujadores p")
    , @NamedQuery(name = "Pujadores.findBySubasta", query = "SELECT p FROM Pujadores p WHERE p.pujadoresPK.subasta = :subasta")
    , @NamedQuery(name = "Pujadores.findByUsuario", query = "SELECT p FROM Pujadores p WHERE p.pujadoresPK.usuario = :usuario")
    , @NamedQuery(name = "Pujadores.findByValorPuja", query = "SELECT p FROM Pujadores p WHERE p.valorPuja = :valorPuja")
    , @NamedQuery(name = "Pujadores.findByFecha", query = "SELECT p FROM Pujadores p WHERE p.fecha = :fecha")})
public class Pujadores implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PujadoresPK pujadoresPK;
    @Size(max = 45)
    @Column(name = "VALOR_PUJA")
    private String valorPuja;
    @Column(name = "FECHA")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @JoinColumn(name = "SUBASTA", referencedColumnName = "idSUBASTA", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Subasta subasta1;
    @JoinColumn(name = "USUARIO", referencedColumnName = "idUSUARIO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario usuario1;

    public Pujadores() {
    }

    public Pujadores(PujadoresPK pujadoresPK) {
        this.pujadoresPK = pujadoresPK;
    }

    public Pujadores(int subasta, int usuario) {
        this.pujadoresPK = new PujadoresPK(subasta, usuario);
    }

    public PujadoresPK getPujadoresPK() {
        return pujadoresPK;
    }

    public void setPujadoresPK(PujadoresPK pujadoresPK) {
        this.pujadoresPK = pujadoresPK;
    }

    public String getValorPuja() {
        return valorPuja;
    }

    public void setValorPuja(String valorPuja) {
        this.valorPuja = valorPuja;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Subasta getSubasta1() {
        return subasta1;
    }

    public void setSubasta1(Subasta subasta1) {
        this.subasta1 = subasta1;
    }

    public Usuario getUsuario1() {
        return usuario1;
    }

    public void setUsuario1(Usuario usuario1) {
        this.usuario1 = usuario1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pujadoresPK != null ? pujadoresPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pujadores)) {
            return false;
        }
        Pujadores other = (Pujadores) object;
        if ((this.pujadoresPK == null && other.pujadoresPK != null) || (this.pujadoresPK != null && !this.pujadoresPK.equals(other.pujadoresPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Pujadores[ pujadoresPK=" + pujadoresPK + " ]";
    }
    
}
