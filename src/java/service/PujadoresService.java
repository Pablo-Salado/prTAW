/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.NotificacionesFacade;
import dao.PujadoresFacade;
import dto.PujadoresDTO;
import entity.Notificaciones;
import entity.Producto;
import entity.Pujadores;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;
import entity.Subasta;
import entity.Usuario;
import java.util.ArrayList;
import java.util.Date;
/**
 *
 * @author Gorpax
 */
@Stateless
public class PujadoresService {
    @EJB PujadoresFacade pujadoresFC;
    
    public void rellenarPujador(Pujadores pujadores,Usuario user,Subasta subasta,double puja,Date fecha) {
        
        pujadores.setUsuario(user);
        pujadores.setSubasta(subasta);
        pujadores.setValorPuja(puja);
        pujadores.setFecha(fecha);
        
    }
    public void crearPujador(Usuario user,Subasta subasta,double puja,Date fecha){
        
        Pujadores pujadores = new Pujadores();
        
        this.rellenarPujador(pujadores, user, subasta, puja, fecha);
        
        this.pujadoresFC.create(pujadores);
    }
    
    
    public List<PujadoresDTO> listaEntityADTO (List<Pujadores> lista) {
        List<PujadoresDTO> listaDTO = null;
        if (lista != null) {
            listaDTO = new ArrayList<>();
            for (Pujadores dc:lista) {
                listaDTO.add(dc.toDTO());
            }
        }
        return listaDTO;
    }
    
    public List<Integer>  buscarPujadoresSubasta(Subasta subasta){
        return this.pujadoresFC.getPujadores(subasta);
    }
    
    public Integer buscarPujadorMaximo(Subasta subasta){
        return this.pujadoresFC.getPujadorMaximo(subasta);
    }
}
