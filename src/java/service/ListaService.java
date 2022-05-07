/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.ListaFacade;
import entity.Lista;
import entity.Usuario;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author javie
 */
@Stateless
public class ListaService {
    @EJB ListaFacade listaFC;
   
    public void crear(Lista nuevo){
        listaFC.create(nuevo);
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
    
    public Lista buscar(Integer idLista){
        return listaFC.find(idLista);
    }

    public void editar(Lista nueva) {
        listaFC.edit(nueva);
    }
}
