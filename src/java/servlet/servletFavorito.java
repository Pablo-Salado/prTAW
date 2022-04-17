/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dao.ProductoFacade;
import dao.SubastaFacade;
import dao.UsuarioFacade;
import entity.Producto;
import entity.Pujadores;
import entity.Subasta;
import entity.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Gorpax
 */
public class servletFavorito extends TAWServlet {
    @EJB SubastaFacade subastaFacade;
    @EJB UsuarioFacade usuarioFacade;
    @EJB ProductoFacade productoFacade;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if(super.comprobarSession(request, response)){
            
         
        String str = request.getParameter("usuario");
        Usuario user = this.usuarioFacade.find(Integer.parseInt(str));
       
        str = request.getParameter("subasta");
        Subasta sub = this.subastaFacade.find(Integer.parseInt(str));
        
        String chck = request.getParameter("favorito");
        
        Producto pro = sub.getProducto();
        
        List<Subasta> subastas = this.subastaFacade.findAll();
        
        List<Producto> productos = new ArrayList<Producto>();
        List<Integer> idPro = this.productoFacade.productosFavoritos( user);
        for(Integer i: idPro){
            Producto aux = this.productoFacade.find(i);
            if(!productos.contains(aux)){
                productos.add(aux);
            }
        }
        
        if(chck == null){
            this.productoFacade.noFav(pro, user);
            productos.remove(pro);
        }else{
            this.productoFacade.Fav(pro, user);
            productos.add(pro);
        }
        
       
        request.setAttribute("subastas", subastas);
        request.setAttribute("productos", productos);
        request.getRequestDispatcher("subastas.jsp").forward(request, response);        
        }
         
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
