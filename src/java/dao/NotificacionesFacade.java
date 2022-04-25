/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Notificaciones;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Gorpax
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
    
}
