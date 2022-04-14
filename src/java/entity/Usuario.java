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
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")
    , @NamedQuery(name = "Usuario.findByIdUSUARIO", query = "SELECT u FROM Usuario u WHERE u.idUSUARIO = :idUSUARIO")
    , @NamedQuery(name = "Usuario.findByNombre", query = "SELECT u FROM Usuario u WHERE u.nombre = :nombre")
    , @NamedQuery(name = "Usuario.findByEpellidos", query = "SELECT u FROM Usuario u WHERE u.epellidos = :epellidos")
    , @NamedQuery(name = "Usuario.findBySexo", query = "SELECT u FROM Usuario u WHERE u.sexo = :sexo")
    , @NamedQuery(name = "Usuario.findByPassword", query = "SELECT u FROM Usuario u WHERE u.password = :password")
    , @NamedQuery(name = "Usuario.findByEmail", query = "SELECT u FROM Usuario u WHERE u.email = :email")})
public class Usuario implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 13)
    @Column(name = "TIPO_USUARIO")
    private String tipoUsuario;

    @Size(max = 45)
    @Column(name = "DOMICILIO")
    private String domicilio;
    @Size(max = 45)
    @Column(name = "CIUDAD_RESIDENCIA")
    private String ciudadResidencia;
    @Column(name = "EDAD")
    private Integer edad;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario1")
    private List<Pujadores> pujadoresList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idUSUARIO")
    private Integer idUSUARIO;
    @Size(max = 45)
    @Column(name = "NOMBRE")
    private String nombre;
    @Size(max = 45)
    @Column(name = "EPELLIDOS")
    private String epellidos;
    @Size(max = 1)
    @Column(name = "SEXO")
    private String sexo;
    @Size(max = 45)
    @Column(name = "PASSWORD")
    private String password;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 45)
    @Column(name = "EMAIL")
    private String email;
    @ManyToMany(mappedBy = "usuarioList")
    private List<Producto> productoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vendedor")
    private List<Subasta> subastaList;
    @OneToMany(mappedBy = "comprador")
    private List<Subasta> subastaList1;

    public Usuario() {
    }

    public Usuario(Integer idUSUARIO) {
        this.idUSUARIO = idUSUARIO;
    }

    public Integer getIdUSUARIO() {
        return idUSUARIO;
    }

    public void setIdUSUARIO(Integer idUSUARIO) {
        this.idUSUARIO = idUSUARIO;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEpellidos() {
        return epellidos;
    }

    public void setEpellidos(String epellidos) {
        this.epellidos = epellidos;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @XmlTransient
    public List<Producto> getProductoList() {
        return productoList;
    }

    public void setProductoList(List<Producto> productoList) {
        this.productoList = productoList;
    }

    @XmlTransient
    public List<Subasta> getSubastaList() {
        return subastaList;
    }

    public void setSubastaList(List<Subasta> subastaList) {
        this.subastaList = subastaList;
    }

    @XmlTransient
    public List<Subasta> getSubastaList1() {
        return subastaList1;
    }

    public void setSubastaList1(List<Subasta> subastaList1) {
        this.subastaList1 = subastaList1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUSUARIO != null ? idUSUARIO.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.idUSUARIO == null && other.idUSUARIO != null) || (this.idUSUARIO != null && !this.idUSUARIO.equals(other.idUSUARIO))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Usuario[ idUSUARIO=" + idUSUARIO + " ]";
    }

    @XmlTransient
    public List<Pujadores> getPujadoresList() {
        return pujadoresList;
    }

    public void setPujadoresList(List<Pujadores> pujadoresList) {
        this.pujadoresList = pujadoresList;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getCiudadResidencia() {
        return ciudadResidencia;
    }

    public void setCiudadResidencia(String ciudadResidencia) {
        this.ciudadResidencia = ciudadResidencia;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
    
}
