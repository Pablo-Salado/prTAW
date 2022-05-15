/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author migue
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "ProyectoTAWPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    public Usuario comprobarUsuario (String strusuario, String strclave) {
        Query q;
        
        q = this.getEntityManager().createQuery("SELECT a FROM Usuario a WHERE a.email = :usuario and " +
                "a.password = :clave");
        
        q.setParameter("usuario", strusuario);
        q.setParameter("clave", strclave);
        List<Usuario> lista = q.getResultList();
        if (lista == null || lista.isEmpty()) {
            return null;
        } else {
            return lista.get(0);
        }        
    }

    private int nonEmptyParams(String nombre, String apellidos, String minEdad, String maxEdad, String tipo_usuario) {
        int nonEmptyParam = 0;
        if(!emptyParam(nombre))
            nonEmptyParam++;
        if(!emptyParam(apellidos))
            nonEmptyParam++;
        if(!emptyParam(minEdad))
            nonEmptyParam++;
        if(!emptyParam(maxEdad))
            nonEmptyParam++;
        if(!tipo_usuario.contains("Tipo de usuario"))
            nonEmptyParam++;
        
        return nonEmptyParam;
    }

    private boolean emptyParam(String param) {
        return param.equals("");
    }
    
    public List<Usuario> filtrarUsuarios(String nombre, String apellidos, String minEdad, String maxEdad, String tipo_usuario){        
        String query = "SELECT u FROM Usuario u ";
        
        int numAnds = nonEmptyParams(nombre, apellidos, minEdad, maxEdad, tipo_usuario);
        
        if(numAnds != 0){
            query += "WHERE ";
        }
        //int params = numAnds;
        if(!emptyParam(nombre)){
            query += "u.nombre LIKE :nombre";
            if(numAnds>1){
                query += " AND ";
                numAnds--;
            }
        }
        if(!emptyParam(apellidos)){
            query += "u.epellidos LIKE :apellidos";
            if(numAnds>1){
                query += " AND ";
                numAnds--;
            }
        }
        if(!emptyParam(minEdad)){
            query += "u.edad > :min";
            if(numAnds>1){
                query += " AND ";
                numAnds--;
            }
        }
        if(!emptyParam(maxEdad)){
            query += "u.edad < :max";
            if(numAnds>1){
                query += " AND ";
                numAnds--;
            }
        }
        if(!tipo_usuario.contains("Tipo de usuario")){
            query += "u.tipoUsuario LIKE :tipoUsuario";
            if(numAnds>1){
                query += " AND ";
                numAnds--;
            }
        }
        Query q = this.getEntityManager().createQuery(query);
            if(!emptyParam(nombre)){
                q.setParameter("nombre", "%"+nombre+"%");
            }
                
            if(!emptyParam(apellidos)){
                q.setParameter("apellidos", "%"+apellidos+"%");
            }
            
            if(!emptyParam(minEdad)){
                q.setParameter("min", Integer.parseInt(minEdad));
            }
            
            if(!emptyParam(maxEdad)){
                q.setParameter("max", Integer.parseInt(maxEdad));
            }
                
            if(!tipo_usuario.contains("Tipo de usuario")){
                q.setParameter("tipoUsuario", "%"+tipo_usuario+"%");
            }
            List<Usuario> usuariosFiltrados = q.getResultList();
            return usuariosFiltrados;
    }
}
