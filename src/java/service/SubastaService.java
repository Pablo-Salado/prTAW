/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;
import dao.SubastaFacade;
import dto.SubastaDTO;
import entity.Producto;
import entity.Subasta;
import entity.Usuario;
import java.util.ArrayList;
import java.util.Date;


/**
 *
 * @author Gorpax
 */
@Stateless
public class SubastaService {
    @EJB SubastaFacade subFC;

    
    
    private List<SubastaDTO> listaEntityADTO (List<Subasta> lista) {
        List<SubastaDTO> listaDTO = null;
        if (lista != null) {
            listaDTO = new ArrayList<>();
            for (Subasta dc:lista) {
                listaDTO.add(dc.toDTO());
            }
        }
        return listaDTO;
    }
    
    
    public SubastaDTO buscarSubasta(Integer id) {
        return this.subFC.find(id).toDTO();
    }

    public List<SubastaDTO> listarSubastas() {
        List<Subasta> list = this.subFC.findAll();
        return listaEntityADTO(list);
    }

    public List<SubastaDTO> filtrarSubastasFavoritas(String categoria, String min, String max, String nombre, String fav,List<Producto> productos) {
        List<Subasta> subastas = new ArrayList<Subasta>();
        List<Subasta> aux = new ArrayList<Subasta>();
        if(fav != null){
                subastas = this.subFC.filtrarSubasta(categoria, min, max, nombre);
                for(Subasta s: subastas){
                    for(Producto p:productos){
                        if(s.getProducto().getIdPRODUCTO() == p.getIdPRODUCTO() && s.getComprador()==null){
                            aux.add(s);
                        }
                    }
                }
                subastas = aux;
   
        }else{
            subastas = this.subFC.filtrarSubasta(categoria, min, max,nombre);
            for(Subasta s: subastas){
                if(s.getComprador()==null){
                    aux.add(s);
                }
            }
            subastas = aux;
        }
        return listaEntityADTO(subastas);
    }

    public List<SubastaDTO> filtrarSubastas(String categoria, String min, String max, String nombre){
        List<Subasta> list =this.subFC.filtrarSubasta(categoria, min, max, nombre);
       return listaEntityADTO(list);
    }
    
    public void borrarSubasta(Integer id) {
        Subasta subasta = this.subFC.find(id);

        this.subFC.remove(subasta);
    }

    public void rellenarSubasta(Subasta subasta, Date fecha_apertura, Date fecha_cierre, double puja_maxima, double precio_inicial, Usuario vendedor, Usuario comprador, Producto producto) {
        
        subasta.setApertura(fecha_apertura);
        subasta.setCierre(fecha_cierre);
        subasta.setPrecioInicial(precio_inicial);
        subasta.setPujaMaxima(puja_maxima);
        subasta.setVendedor(vendedor);
        subasta.setComprador(comprador);
        subasta.setProducto(producto);
        
        
    }
    public void crearSubasta(Date fecha_apertura, Date fecha_cierre, double puja_maxima, double precio_inicial, Usuario vendedor, Usuario comprador, Producto producto){
        
        Subasta subasta = new Subasta();
        
        this.rellenarSubasta(subasta, fecha_apertura, fecha_cierre, puja_maxima, precio_inicial, vendedor, comprador, producto);
        
        this.subFC.create(subasta);
    }
    public void modificarSubasta(Integer id,Date fecha_apertura, Date fecha_cierre, double puja_maxima, double precio_inicial, Usuario vendedor, Usuario comprador, Producto producto){
        Subasta subasta = this.subFC.find(id);
        
        this.rellenarSubasta(subasta, fecha_apertura, fecha_cierre, puja_maxima, precio_inicial, vendedor, comprador, producto);
        
        this.subFC.edit(subasta);
    }
    public void modificarPujaMaxima(Integer id, double puja_maxima){
        Subasta subasta = this.subFC.find(id);
        
        subasta.setPujaMaxima(puja_maxima);
        
        this.subFC.edit(subasta);
    }
    
    public void modificarFechaCierre(Integer id,Date fecha_cierre){
        Subasta subasta = this.subFC.find(id);
        
        subasta.setCierre(fecha_cierre);
        
        this.subFC.edit(subasta);
    }
    public void modificarComprador(Integer id,Usuario comprador){
        Subasta subasta = this.subFC.find(id);
        
        subasta.setComprador(comprador);
        this.subFC.edit(subasta);
    }
    
}
