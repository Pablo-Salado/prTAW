/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Pujadores;
import entity.Subasta;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Gorpax
 */
@Stateless
public class PujadoresFacade extends AbstractFacade<Pujadores> {

    @PersistenceContext(unitName = "ProyectoTAWPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PujadoresFacade() {
        super(Pujadores.class);
    }
    
    
    public List<Integer> getPujadores (Subasta sub) {
        Query q;
       int idSubasta = sub.getIdSUBASTA();
        q = this.getEntityManager().createNativeQuery("select USUARIO from pujadores s where s.subasta = " + idSubasta);

        return q.getResultList();
    }
    
}
