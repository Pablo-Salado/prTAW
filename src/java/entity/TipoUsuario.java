/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "tipo_usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoUsuario.findAll", query = "SELECT t FROM TipoUsuario t")
    , @NamedQuery(name = "TipoUsuario.findByTipo", query = "SELECT t FROM TipoUsuario t WHERE t.tipoUsuarioPK.tipo = :tipo")
    , @NamedQuery(name = "TipoUsuario.findByUsuario", query = "SELECT t FROM TipoUsuario t WHERE t.tipoUsuarioPK.usuario = :usuario")})
public class TipoUsuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TipoUsuarioPK tipoUsuarioPK;

    public TipoUsuario() {
    }

    public TipoUsuario(TipoUsuarioPK tipoUsuarioPK) {
        this.tipoUsuarioPK = tipoUsuarioPK;
    }

    public TipoUsuario(String tipo, int usuario) {
        this.tipoUsuarioPK = new TipoUsuarioPK(tipo, usuario);
    }

    public TipoUsuarioPK getTipoUsuarioPK() {
        return tipoUsuarioPK;
    }

    public void setTipoUsuarioPK(TipoUsuarioPK tipoUsuarioPK) {
        this.tipoUsuarioPK = tipoUsuarioPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipoUsuarioPK != null ? tipoUsuarioPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoUsuario)) {
            return false;
        }
        TipoUsuario other = (TipoUsuario) object;
        if ((this.tipoUsuarioPK == null && other.tipoUsuarioPK != null) || (this.tipoUsuarioPK != null && !this.tipoUsuarioPK.equals(other.tipoUsuarioPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.TipoUsuario[ tipoUsuarioPK=" + tipoUsuarioPK + " ]";
    }
    
}
