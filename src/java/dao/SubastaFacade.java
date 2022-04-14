/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Subasta;
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
public class SubastaFacade extends AbstractFacade<Subasta> {

    @PersistenceContext(unitName = "ProyectoTAWPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SubastaFacade() {
        super(Subasta.class);
    }
    
     public List<Subasta> findByPrecio (String min,String max) {
        Query q;
        int minimo = Integer.valueOf(min);
        int maximo = Integer.valueOf(max);
        q = this.getEntityManager().createQuery("select s from Subasta s where s.precioInicial >=  :minimo AND s.precioInicial <= :maximo");
        q.setParameter("minimo", minimo);
        q.setParameter("maximo", maximo);
        
        return q.getResultList();
    }
    public List<Subasta> findByMin (String min) {
        Query q;
        int minimo = Integer.valueOf(min);
        q = this.getEntityManager().createQuery("select s from Subasta s where s.precioInicial >= :minimo");
        q.setParameter("minimo", minimo);
        
        return q.getResultList();
    }
    public List<Subasta> findByMax (String max) {
        Query q;
        int maximo = Integer.valueOf(max);
        q = this.getEntityManager().createQuery("select s from Subasta s where s.precioInicial <= :maximo");
        q.setParameter("maximo", maximo);
        
        return q.getResultList();
    }
    public List<Subasta> findByCategoria (String cat) {
        Query q;
        q = this.getEntityManager().createQuery("select s from Subasta s where s.producto.categoria = :cat");
        q.setParameter("cat", cat);
        
        return q.getResultList();
    }
    public List<Subasta> findByCategoriaMin (String cat, String min) {
        Query q;
        int minimo = Integer.valueOf(min);
        q = this.getEntityManager().createQuery("select s from Subasta s where s.producto.categoria = :cat AND s.precioInicial >= :minimo");
        q.setParameter("cat", cat);
        q.setParameter("minimo", minimo);
        
        return q.getResultList();
    }
    
    public List<Subasta> findByCategoriaMax (String cat, String max) {
        Query q;
        int maximo = Integer.valueOf(max);
        q = this.getEntityManager().createQuery("select s from Subasta s where s.producto.categoria = :cat AND s.precioInicial <= :maximo");
        q.setParameter("cat", cat);
        q.setParameter("maximo", maximo);
        
        return q.getResultList();
    }
    public List<Subasta> findByCategoriaPrecio (String cat, String min, String max) {
        Query q;
        int maximo = Integer.valueOf(max);
        int minimo = Integer.valueOf(min);
        q = this.getEntityManager().createQuery("select s from Subasta s where s.producto.categoria = :cat AND s.precioInicial <= :maximo AND s.precioInicial >= :minimo");
        q.setParameter("cat", cat);
        q.setParameter("maximo", maximo);
        q.setParameter("minimo", minimo);
        
        return q.getResultList();
    }
}
