/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.NotificacionesFacade;
import entity.Notificaciones;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;
import entity.Subasta;
import entity.Usuario;

/**
 *
 * @author Gorpax
 */
@Stateless
public class NotificacionesService {
    @EJB NotificacionesFacade notiFC;
    
    public Notificaciones buscarNotificacion(Integer id) {
        return this.notiFC.find(id);
    }

    public List<Notificaciones> listarNotificaciones(){
        return this.notiFC.findAll();
    }
    
    public List<Notificaciones> listarNotificacionesPorUsuario(Integer id){
        return this.notiFC.notificacionUsuario(id);
    }
    
    public void rellenarNotificacion(Notificaciones not,Usuario user,Subasta subasta,String ganador) {
        
        not.setIdUsuario(user);
        not.setIdSubasta(subasta);
        not.setGanador(ganador);
        
    }
    public void crearSubasta(Usuario user,Subasta subasta,String ganador){
        
        Notificaciones notificacion = new Notificaciones();
        
        this.rellenarNotificacion(notificacion, user, subasta, ganador);
        
        this.notiFC.create(notificacion);
    }
    public void borrarNotiPorId(Integer id) {
        Notificaciones not = this.notiFC.find(id);

        this.notiFC.remove(not);
    }
}
