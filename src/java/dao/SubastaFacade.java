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
 * @author migue
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
    public List<Subasta> obtenerSubastasUsuario(Integer idUsuario){
        Query q;
        
        q = this.getEntityManager().createQuery("SELECT s FROM Subasta s WHERE s.vendedor.idUSUARIO = :idUsuario");
        q.setParameter("idUsuario", idUsuario);
        List<Subasta> res = (List<Subasta>)q.getResultList();
        return res;
    }
    
    public List<Subasta> filtrarSubasta(String cat, String min, String max, String nombre){
       Query q; 
       
       if(cat.contains("CATEGORIAS") && (min == null || max == null || (min.length()==0 && max.length()==0)) && nombre.length() == 0){
           q = this.getEntityManager().createQuery("select s from Subasta s");
           
       }else if(nombre.length() == 0){
           if(cat.contains("CATEGORIAS")){
                if(min.length()== 0 && max.length() > 0){
                    int maximo = Integer.valueOf(max);
                    q = this.getEntityManager().createQuery("select s from Subasta s where s.pujaMaxima <= :maximo");
                    q.setParameter("maximo", maximo);
                }else if(min.length()> 0 && max.length() == 0){
                    int minimo = Integer.valueOf(min);
                    q = this.getEntityManager().createQuery("select s from Subasta s where s.pujaMaxima >= :minimo");
                    q.setParameter("minimo", minimo);
                }else{
                    int minimo = Integer.valueOf(min);
                    int maximo = Integer.valueOf(max);
                     q = this.getEntityManager().createQuery("select s from Subasta s where s.pujaMaxima >=  :minimo AND s.pujaMaxima <= :maximo");
                     q.setParameter("minimo", minimo);
                     q.setParameter("maximo", maximo);
                }
             }else{
                if(min.length()== 0 && max.length() > 0){
                    int maximo = Integer.valueOf(max);
                     q = this.getEntityManager().createQuery("select s from Subasta s where s.producto.categoria = :cat AND s.pujaMaxima <= :maximo");
                     q.setParameter("cat", cat);
                     q.setParameter("maximo", maximo);
                }else if(min.length()> 0 && max.length() == 0){
                    int minimo = Integer.valueOf(min);
                     q = this.getEntityManager().createQuery("select s from Subasta s where s.producto.categoria = :cat AND s.pujaMaxima >= :minimo");
                     q.setParameter("cat", cat);
                     q.setParameter("minimo", minimo);
                }else if(min.length()> 0 && max.length() > 0){
                    int maximo = Integer.valueOf(max);
                     int minimo = Integer.valueOf(min);
                     q = this.getEntityManager().createQuery("select s from Subasta s where s.producto.categoria = :cat AND s.pujaMaxima <= :maximo AND s.pujaMaxima >= :minimo");
                     q.setParameter("cat", cat);
                     q.setParameter("maximo", maximo);
                     q.setParameter("minimo", minimo);
                }else{
                    q = this.getEntityManager().createQuery("select s from Subasta s where s.producto.categoria = :cat");
                     q.setParameter("cat", cat);
                }
       }
       }else{
           if(cat.contains("CATEGORIAS")){
                if(min.length()== 0 && max.length() > 0){
                    int maximo = Integer.valueOf(max);
                    q = this.getEntityManager().createQuery("select s from Subasta s where s.pujaMaxima <= :maximo AND s.producto.titulo like :nombre");
                    q.setParameter("maximo", maximo);
                    q.setParameter("nombre", '%' + nombre + '%');
                }else if(min.length()> 0 && max.length() == 0){
                    int minimo = Integer.valueOf(min);
                    q = this.getEntityManager().createQuery("select s from Subasta s where s.pujaMaxima >= :minimo AND s.producto.titulo like :nombre");
                    q.setParameter("minimo", minimo);
                    q.setParameter("nombre", '%' + nombre + '%');
                }else if(min.length()> 0 && max.length() > 0){
                    int minimo = Integer.valueOf(min);
                    int maximo = Integer.valueOf(max);
                     q = this.getEntityManager().createQuery("select s from Subasta s where s.pujaMaxima >=  :minimo AND s.pujaMaxima <= :maximo AND s.producto.titulo like :nombre");
                     q.setParameter("minimo", minimo);
                     q.setParameter("maximo", maximo);
                     q.setParameter("nombre", '%' + nombre + '%');
                }else{
                    q = this.getEntityManager().createQuery("select s from Subasta s where s.producto.titulo like :nombre");
                     q.setParameter("nombre", '%' + nombre + '%');
                }
            }else{
                if(min.length()== 0 && max.length() > 0){
                    int maximo = Integer.valueOf(max);
                     q = this.getEntityManager().createQuery("select s from Subasta s where s.producto.categoria = :cat AND s.pujaMaxima <= :maximo AND s.producto.titulo like :nombre ");
                     q.setParameter("cat", cat);
                     q.setParameter("maximo", maximo);
                     q.setParameter("nombre", '%' + nombre + '%');
                }else if(min.length()> 0 && max.length() == 0){
                    int minimo = Integer.valueOf(min);
                     q = this.getEntityManager().createQuery("select s from Subasta s where s.producto.categoria = :cat AND s.pujaMaxima >= :minimo AND s.producto.titulo like :nombre");
                     q.setParameter("cat", cat);
                     q.setParameter("minimo", minimo);
                     q.setParameter("nombre", '%' + nombre + '%');
                }else if(min.length()> 0 && max.length() > 0){
                    int maximo = Integer.valueOf(max);
                     int minimo = Integer.valueOf(min);
                     q = this.getEntityManager().createQuery("select s from Subasta s where s.producto.categoria = :cat AND s.pujaMaxima <= :maximo AND s.pujaMaxima >= :minimo AND s.producto.titulo like :nombre");
                     q.setParameter("cat", cat);
                     q.setParameter("maximo", maximo);
                     q.setParameter("minimo", minimo);
                     q.setParameter("nombre", '%' + nombre + '%');
                }else{
                    q = this.getEntityManager().createQuery("select s from Subasta s where s.producto.categoria = :cat AND s.producto.titulo like :nombre");
                     q.setParameter("cat", cat);
                    q.setParameter("nombre", '%' + nombre + '%');
                }
       } 
       }
       
       
       return q.getResultList();
    }
    
    
}
