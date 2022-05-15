/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.NotificacionesFacade;
import dao.PujadoresFacade;
import dao.SubastaFacade;
import dao.UsuarioFacade;
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
 * @author Alex Medina 50%
 *         Miguel Angel Cosano 50%
 */
@Stateless
public class PujadoresService {
    @EJB PujadoresFacade pujadoresFC;
    @EJB UsuarioFacade usuarioFC;
    @EJB SubastaFacade subastaFC;
    
    public void rellenarPujador(Integer pujadoresId,Integer userId,Integer subastaId,double puja,Date fecha) {
        Pujadores pujadores=this.pujadoresFC.find(pujadoresId);
        Usuario user = this.usuarioFC.find(userId);
        Subasta subasta = this.subastaFC.find(subastaId);
        
        pujadores.setUsuario(user);
        pujadores.setSubasta(subasta);
        pujadores.setValorPuja(puja);
        pujadores.setFecha(fecha);
        
    }
    public void crearPujador(Integer userId,Integer subastaId,double puja,Date fecha){
        
        Usuario user = this.usuarioFC.find(userId);
        Subasta sub = this.subastaFC.find(subastaId);
        
        Pujadores pujadores = new Pujadores();
        
        pujadores.setUsuario(user);
        pujadores.setSubasta(sub);
        pujadores.setValorPuja(puja);
        pujadores.setFecha(fecha);
        
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
    
    public List<Integer>  buscarPujadoresSubasta(Integer subastaId){
        Subasta subasta = this.subastaFC.find(subastaId);
        return this.pujadoresFC.getPujadores(subasta);
    }
    
    public Integer buscarPujadorMaximo(Integer subastaId){
        Subasta subasta = this.subastaFC.find(subastaId);
        return this.pujadoresFC.getPujadorMaximo(subasta);
    }
}
