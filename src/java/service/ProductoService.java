/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.ProductoFacade;
import entity.Producto;
import entity.Subasta;
import entity.Usuario;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
/**
 *
 * @author Gorpax
 */
@Stateless
public class ProductoService {
    @EJB ProductoFacade proFC;
    
    public Producto buscarProducto(Integer id) {
        return this.proFC.find(id);
    }

    public List<Producto> listarSubastas() {
        return this.proFC.findAll();
    }
    
    public void rellenarProducto(Producto producto,String titulo, String descripcion, String url,String estado, String categoria,Subasta subasta){
        
        producto.setTitulo(titulo);
        producto.setDescripcion(descripcion);
        producto.setUrlFoto(url);
        producto.setEstado(estado);
        producto.setCategoria(categoria);
        producto.setSubasta(subasta);
        
    }
    
    public void crearProducto(String titulo, String descripcion, String url,String estado, String categoria,Subasta subasta){
        Producto producto = new Producto();
        
        this.rellenarProducto(producto, titulo, descripcion, url, estado, categoria, subasta);
        
        this.proFC.create(producto);
    }
    
    public void eliminarProducto(Producto pro){

        this.proFC.remove(pro);
    }
    
    public void eliminarProductoId(Integer id){
        Producto pro = this.proFC.find(id);

        this.proFC.remove(pro);
    }
    
    public void modificarProducto(Producto pro,String titulo, String descripcion, String url,String estado, String categoria,Subasta subasta){
        this.rellenarProducto(pro, titulo, descripcion, url, estado, categoria, subasta);
        
        this.proFC.edit(pro);
    }
    
    public void modificarProductoId(Integer id,String titulo, String descripcion, String url,String estado, String categoria,Subasta subasta){
        Producto pro = this.proFC.find(id);
        
        this.rellenarProducto(pro, titulo, descripcion, url, estado, categoria, subasta);
        
        this.proFC.edit(pro);
    }

    public void addProductoFavorito(Producto pro, Usuario user){
        this.proFC.Fav(pro, user);
    }
    
    public void eliminarProductoFavorito(Producto pro, Usuario user){
        this.proFC.noFav(pro, user);
    }
    
    public List<Integer> listaFavoritos(Usuario user){
        return this.proFC.productosFavoritos(user);
    }
}
