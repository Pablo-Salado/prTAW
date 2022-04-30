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
import entity.Subasta;
import entity.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author X430F
 */
public class servletAdminFiltrarSubastas extends HttpServlet {
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
        String min = request.getParameter("minPrice");
        String max = request.getParameter("maxPrice");
        String cat = request.getParameter("categoria");
        List<Subasta> subastas = null;
        if(cat == null || cat.contains("CATEGORIAS")){
            if(min == null || max == null || (min.length()==0 && max.length()==0)){
                subastas = this.subastaFacade.findAll();
                
            }else if ((min.length()>0 && max.length() > 0)){
                subastas = this.subastaFacade.findByPrecio(min,max);
            }else if(min.length()> 0 && max.length() == 0){
                subastas = this.subastaFacade.findByMin(min);
            }else if(min.length()== 0 && max.length() > 0){
                subastas = this.subastaFacade.findByMax(max);
                
            }
        }else{
            if(min == null || max == null || (min.length()==0 && max.length()==0)){
                subastas = this.subastaFacade.findByCategoria(cat);
            }else if ((min.length()>0 && max.length() > 0)){
                subastas = this.subastaFacade.findByCategoriaPrecio(cat,min,max);
            }else if(min.length()> 0 && max.length() == 0){
                subastas = this.subastaFacade.findByCategoriaMin(cat,min);
            }else if(min.length()== 0 && max.length() > 0){
                subastas = this.subastaFacade.findByCategoriaMax(cat,max);
            }
        }
        String str = request.getParameter("usuario");
        Usuario user = this.usuarioFacade.find(Integer.parseInt(str));
        List<Producto> productos = new ArrayList<Producto>();
        List<Integer> idPro = this.productoFacade.productosFavoritos( user);
        for(Integer i: idPro){
            Producto aux = this.productoFacade.find(i);
            if(!productos.contains(aux)){
                productos.add(aux);
            }
        }
        request.setAttribute("productos", productos);
        request.setAttribute("subastas", subastas);
        
        request.getRequestDispatcher("admin.jsp").forward(request, response);
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