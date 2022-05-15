/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import dto.ListaDTO;
import java.io.Serializable;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author javie
 */
@Entity
@Table(name = "lista")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Lista.findAll", query = "SELECT l FROM Lista l")
    , @NamedQuery(name = "Lista.findByIdLISTA", query = "SELECT l FROM Lista l WHERE l.idLISTA = :idLISTA")
    , @NamedQuery(name = "Lista.findByNombre", query = "SELECT l FROM Lista l WHERE l.nombre = :nombre")
    , @NamedQuery(name = "Lista.findByDescripcion", query = "SELECT l FROM Lista l WHERE l.descripcion = :descripcion")
    , @NamedQuery(name = "Lista.findByAtributos", query = "SELECT l FROM Lista l WHERE l.atributos = :atributos")})
public class Lista implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idLISTA")
    private Integer idLISTA;
    @Size(max = 45)
    @Column(name = "NOMBRE")
    private String nombre;
    @Size(max = 45)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Size(max = 45)
    @Column(name = "ATRIBUTOS")
    private String atributos;
    @JoinColumn(name = "USUARIO", referencedColumnName = "idUSUARIO")
    @ManyToOne(optional = false)
    private Usuario usuario;

    public Lista() {
    }

    public Lista(Integer idLISTA) {
        this.idLISTA = idLISTA;
    }

    public Integer getIdLISTA() {
        return idLISTA;
    }

    public void setIdLISTA(Integer idLISTA) {
        this.idLISTA = idLISTA;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getAtributos() {
        return atributos;
    }

    public void setAtributos(String atributos) {
        this.atributos = atributos;
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
        hash += (idLISTA != null ? idLISTA.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lista)) {
            return false;
        }
        Lista other = (Lista) object;
        if ((this.idLISTA == null && other.idLISTA != null) || (this.idLISTA != null && !this.idLISTA.equals(other.idLISTA))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Lista[ idLISTA=" + idLISTA + " ]";
    }
    
    public ListaDTO toDTO(){
        ListaDTO res = new ListaDTO();
        res.setIdLISTA(idLISTA);
        res.setAtributos(atributos);
        res.setDescripcion(descripcion);
        res.setNombre(nombre);
        res.setUsuario(usuario);
        return res;
    }
}
