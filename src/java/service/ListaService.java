/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.ListaFacade;
import dao.UsuarioFacade;
import dto.ListaDTO;
import entity.Lista;
import entity.Usuario;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Javier Santiburcio
 */
@Stateless
public class ListaService {
    @EJB ListaFacade listaFC;
    @EJB UsuarioFacade userFC;
    
    public ListaService(){}
   
    public void crear(ListaDTO nuevo){
        
        Lista lista = new Lista();
        lista.setAtributos(nuevo.getAtributos());
        lista.setDescripcion(nuevo.getDescripcion());
        lista.setNombre(nuevo.getNombre());
        lista.setUsuario(nuevo.getUsuario());
        listaFC.create(lista);
    }
    
    public List<Lista> todas(){
        return listaFC.findAll();
    }

    public void borrar(Integer idLista) {
       listaFC.remove(listaFC.find(idLista));
    }

    public List<Lista> getListasPorUsuario(Usuario usuario) {
         return this.listaFC.findAll().stream().filter(l -> l.getUsuario() == usuario).collect(Collectors.toList());
    }
    
    public ListaDTO buscar(Integer idLista){
        return listaFC.find(idLista).toDTO();
    }

    public void editar(ListaDTO nueva) {
        Lista lista = listaFC.find(nueva.getIdLISTA());
        lista.setIdLISTA(nueva.getIdLISTA());
        lista.setAtributos(nueva.getAtributos());
        lista.setDescripcion(nueva.getDescripcion());
        lista.setNombre(nueva.getNombre());
        lista.setUsuario(nueva.getUsuario());
        listaFC.edit(lista);
    }

    public List<ListaDTO> listaTODTO(List<Lista> listaList) {
        List<ListaDTO> listaDTO = null;
        if (listaList != null) {
            listaDTO = new ArrayList<>();
            for (Lista l : listaList) {
                ListaDTO list = new ListaDTO();
                list.setAtributos(l.getAtributos());
                list.setDescripcion(l.getDescripcion());
                list.setNombre(l.getNombre());
                list.setIdLISTA(l.getIdLISTA());
                list.setUsuario(l.getUsuario());
                listaDTO.add(list);
            }
        }
        return listaDTO;
    }
}
