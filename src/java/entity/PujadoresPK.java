/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Usuario
 */
@Embeddable
public class PujadoresPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "SUBASTA")
    private int subasta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "USUARIO")
    private int usuario;

    public PujadoresPK() {
    }

    public PujadoresPK(int subasta, int usuario) {
        this.subasta = subasta;
        this.usuario = usuario;
    }

    public int getSubasta() {
        return subasta;
    }

    public void setSubasta(int subasta) {
        this.subasta = subasta;
    }

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) subasta;
        hash += (int) usuario;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PujadoresPK)) {
            return false;
        }
        PujadoresPK other = (PujadoresPK) object;
        if (this.subasta != other.subasta) {
            return false;
        }
        if (this.usuario != other.usuario) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.PujadoresPK[ subasta=" + subasta + ", usuario=" + usuario + " ]";
    }
    
}
