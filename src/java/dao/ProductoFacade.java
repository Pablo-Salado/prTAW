/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Producto;
import entity.Subasta;
import entity.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Usuario
 */
@Stateless
public class ProductoFacade extends AbstractFacade<Producto> {

    @PersistenceContext(unitName = "ProyectoTAWPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductoFacade() {
        super(Producto.class);
    }
    
    public void Fav(Producto pro, Usuario user){
        Query q;
       int idProducto = pro.getIdPRODUCTO();
       int idUsuario = user.getIdUSUARIO();
        q = this.getEntityManager().createNativeQuery("INSERT INTO favoritos VALUES(" + idProducto + "," + idUsuario +")");
        q.executeUpdate();
    }
   public void noFav(Producto pro, Usuario user){
        Query q;
       int idProducto = pro.getIdPRODUCTO();
       int idUsuario = user.getIdUSUARIO();
        q = this.getEntityManager().createNativeQuery("DELETE FROM favoritos WHERE PRODUCTO = " + idProducto + " AND USUARIO = " + idUsuario);
        q.executeUpdate();
    }
     public List<Integer> productosFavoritos (Usuario user) {
        Query q;
       int idUsuario = user.getIdUSUARIO();
        q = this.getEntityManager().createNativeQuery("select PRODUCTO from favoritos s where s.USUARIO = " + idUsuario);

        return q.getResultList();
    }
}
