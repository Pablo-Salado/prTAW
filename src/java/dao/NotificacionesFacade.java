/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Notificaciones;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Alejandro Medina
 */
@Stateless
public class NotificacionesFacade extends AbstractFacade<Notificaciones> {

    @PersistenceContext(unitName = "ProyectoTAWPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public NotificacionesFacade() {
        super(Notificaciones.class);
    }
    
    public List<Notificaciones> notificacionUsuario (Integer idUsuario) {
        Query q;
        q = this.getEntityManager().createQuery("select n from Notificaciones n where n.idUsuario.idUSUARIO = :idUsuario " );
        q.setParameter("idUsuario", idUsuario);
        return q.getResultList();
    }
}
