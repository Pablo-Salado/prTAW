/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.NotificacionesFacade;
import dto.NotificacionesDTO;
import entity.Notificaciones;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;
import entity.Subasta;
import entity.Usuario;
import java.util.ArrayList;

/**
 *
 * @author Gorpax
 */
@Stateless
public class NotificacionesService {
    @EJB NotificacionesFacade notiFC;
    
    public List<NotificacionesDTO> listaEntityADTO(List<Notificaciones> lista){
        List<NotificacionesDTO> listaDTO = null;
        if (lista != null) {
            listaDTO = new ArrayList<>();
            for (Notificaciones dc:lista) {
                listaDTO.add(dc.toDTO());
            }
        }
        return listaDTO;
    }
    
    public NotificacionesDTO buscarNotificacion(Integer id) {
        return this.notiFC.find(id).toDTO();
    }

    public List<NotificacionesDTO> listarNotificaciones(){
        List<Notificaciones> lista = this.notiFC.findAll();
        return listaEntityADTO(lista);
    }
    
    public List<NotificacionesDTO> listarNotificacionesPorUsuario(Integer id){
        List<Notificaciones> lista = this.notiFC.notificacionUsuario(id);
        return listaEntityADTO(lista);
        
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
