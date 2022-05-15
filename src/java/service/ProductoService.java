/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.ProductoFacade;
import dao.SubastaFacade;
import dao.UsuarioFacade;
import dto.ProductoDTO;
import entity.Producto;
import entity.Subasta;
import entity.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
/**
 *
 * @author Alex Medina 50%
 *         Miguel Angel Cosano 50%
 */
@Stateless
public class ProductoService {
    @EJB ProductoFacade proFC;
    @EJB UsuarioFacade userFC;
    @EJB SubastaFacade subFC;
    
    public List<ProductoDTO> listaEntityADTO (List<Producto> lista) {

        List<ProductoDTO> listaDTO = null;
        if (lista != null) {
            listaDTO = new ArrayList<>();
            for (Producto dc:lista) {
                listaDTO.add(dc.toDTO());
            }
        }
        return listaDTO;
    }
    
    public ProductoDTO buscarProducto(Integer id) {
        return this.proFC.find(id).toDTO();
    }

    public List<ProductoDTO> listarProductos() {
        List<Producto> lista =this.proFC.findAll();
        return listaEntityADTO(lista);
    }
    
    public void rellenarProducto(Producto producto,String titulo, String descripcion, String url,String estado, String categoria,Integer subastaId){
        if(subastaId!=null){
           Subasta subasta = this.subFC.find(subastaId); 
           producto.setSubasta(subasta);
        }
        
        
        producto.setTitulo(titulo);
        producto.setDescripcion(descripcion);
        producto.setUrlFoto(url);
        producto.setEstado(estado);
        producto.setCategoria(categoria);
        
        
    }
    
    public void crearProducto(String titulo, String descripcion, String url,String estado, String categoria,Integer subastaId){
        Producto producto = new Producto();
        
        this.rellenarProducto(producto, titulo, descripcion, url, estado, categoria,subastaId);
        
        this.proFC.create(producto);
    }
    
    public void eliminarProducto(Integer proId){
        
        Producto pro =this.proFC.find(proId);
        
        this.proFC.remove(pro);
    }
    
    public void eliminarProductoId(Integer id){
        Producto pro = this.proFC.find(id);

        this.proFC.remove(pro);
    }
    
    public void modificarProducto(Integer proId,String titulo, String descripcion, String url,String estado, String categoria,Integer subastaId){
        Producto pro = this.proFC.find(proId);
        
        this.rellenarProducto(pro, titulo, descripcion, url, estado, categoria, subastaId);
        
        this.proFC.edit(pro);
    }
    
    public void modificarProductoId(Integer id,String titulo, String descripcion, String url,String estado, String categoria,Integer subastaId){
        Producto pro = this.proFC.find(id);
        
        this.rellenarProducto(pro, titulo, descripcion, url, estado, categoria, subastaId);
        
        this.proFC.edit(pro);
    }

    public void addProductoFavorito(Integer proId, Integer userId){
        
        Producto pro = this.proFC.find(proId);
        Usuario user = this.userFC.find(userId);
        this.proFC.Fav(pro, user);
    }
    
    public void eliminarProductoFavorito(Integer proId, Integer userId){
        Producto pro = this.proFC.find(proId);
        Usuario user = this.userFC.find(userId);
        this.proFC.noFav(pro, user);
    }
    
    public List<Integer> listaFavoritos(Integer idUser){
        
        return this.proFC.productosFavoritos(this.userFC.find(idUser));
    }

    
    public void modificarSubasta(Integer proId, Integer subId){
        Subasta sub = this.subFC.find(subId);
        Producto pro = this.proFC.find(proId);
        pro.setSubasta(sub);
        
        this.proFC.edit(pro);
    }
    public void modificarEstado(Integer proId, String estado){
        Producto pro = this.proFC.find(proId);
        pro.setEstado(estado);
        
        this.proFC.edit(pro);
    }
}
