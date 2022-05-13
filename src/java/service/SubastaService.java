/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.ProductoFacade;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;
import dao.SubastaFacade;
import dao.UsuarioFacade;
import dto.ProductoDTO;
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
    @EJB ProductoFacade prodFC;
    @EJB UsuarioFacade userFC;
    
    
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
        List<SubastaDTO> lista = listaEntityADTO(list);
        return lista;
        
    }

    public List<SubastaDTO> filtrarSubastasFavoritas(String categoria, String min, String max, String nombre, String fav,List<ProductoDTO> productos) {
        List<Subasta> subastas = new ArrayList<Subasta>();
        List<Subasta> aux = new ArrayList<Subasta>();
        if(fav != null){
                subastas = this.subFC.filtrarSubasta(categoria, min, max, nombre);
                for(Subasta s: subastas){
                    for(ProductoDTO p:productos){
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

    public void rellenarSubasta(Integer subastaId, Date fecha_apertura, Date fecha_cierre, double puja_maxima, double precio_inicial, Integer vendedorId, Integer compradorId, Integer productoId) {
        
        Subasta subasta = this.subFC.find(subastaId);
        Usuario vendedor = this.userFC.find(vendedorId);
        Usuario comprador = this.userFC.find(compradorId);
        Producto producto = this.prodFC.find(productoId);
        
        subasta.setApertura(fecha_apertura);
        subasta.setCierre(fecha_cierre);
        subasta.setPrecioInicial(precio_inicial);
        subasta.setPujaMaxima(puja_maxima);
        subasta.setVendedor(vendedor);
        subasta.setComprador(comprador);
        subasta.setProducto(producto);
        
        
    }
    public void crearSubasta(Date fecha_apertura, Date fecha_cierre, double puja_maxima, double precio_inicial, Integer vendedorId, Integer productoId){
        
        Subasta subasta = new Subasta();
        Usuario vendedor = this.userFC.find(vendedorId);
        
        Producto producto = this.prodFC.find(productoId);
        
        subasta.setApertura(fecha_apertura);
        subasta.setCierre(fecha_cierre);
        subasta.setPrecioInicial(precio_inicial);
        subasta.setPujaMaxima(puja_maxima);
        subasta.setVendedor(vendedor);
        subasta.setComprador(null);
        subasta.setProducto(producto);
        
        this.subFC.create(subasta);
    }
    public void modificarSubasta(Integer subastaId,Date fecha_apertura, Date fecha_cierre, double puja_maxima, double precio_inicial, Integer vendedorId, Integer compradorId, Integer productoId){
        Subasta subasta = this.subFC.find(subastaId);
        Usuario vendedor = this.userFC.find(vendedorId);
        Usuario comprador = this.userFC.find(compradorId);
        Producto producto = this.prodFC.find(productoId);
        
        
        this.rellenarSubasta(subastaId, fecha_apertura, fecha_cierre, puja_maxima, precio_inicial, vendedorId, compradorId, productoId);
        
        this.subFC.edit(subasta);
    }
    public void modificarPujaMaxima(Integer subastaId, double puja_maxima){
        Subasta subasta = this.subFC.find(subastaId);
        
        subasta.setPujaMaxima(puja_maxima);
        
        this.subFC.edit(subasta);
    }
    
    public void modificarFechaCierre(Integer id,Date fecha_cierre){
        Subasta subasta = this.subFC.find(id);
        
        subasta.setCierre(fecha_cierre);
        
        this.subFC.edit(subasta);
    }
    public void modificarComprador(Integer id,Integer compradorId){
        Subasta subasta = this.subFC.find(id);
        Usuario comprador = this.userFC.find(compradorId);
        subasta.setComprador(comprador);
        this.subFC.edit(subasta);
    }
    
}
