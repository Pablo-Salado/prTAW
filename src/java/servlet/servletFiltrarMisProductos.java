/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dao.SubastaFacade;
import entity.Subasta;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Usuario
 */
public class servletFiltrarMisProductos extends TAWServlet {
    @EJB SubastaFacade subastaFacade;
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
           
         String min = request.getParameter("minPrice");
        String max = request.getParameter("maxPrice");
        String cat = request.getParameter("categoria");
        List<Subasta> misCompras = null;
        if(cat == null || cat.contains("CATEGORIAS")){
            if(min == null || max == null || (min.length()==0 && max.length()==0)){
                misCompras = this.subastaFacade.findAll();
                
            }else if ((min.length()>0 && max.length() > 0)){
                misCompras = this.subastaFacade.findByPrecio(min,max);
            }else if(min.length()> 0 && max.length() == 0){
                misCompras = this.subastaFacade.findByMin(min);
            }else if(min.length()== 0 && max.length() > 0){
                misCompras = this.subastaFacade.findByMax(max);
                
            }
        }else{
            if(min == null || max == null || (min.length()==0 && max.length()==0)){
                misCompras = this.subastaFacade.findByCategoria(cat);
            }else if ((min.length()>0 && max.length() > 0)){
                misCompras = this.subastaFacade.findByCategoriaPrecio(cat,min,max);
            }else if(min.length()> 0 && max.length() == 0){
                misCompras = this.subastaFacade.findByCategoriaMin(cat,min);
            }else if(min.length()== 0 && max.length() > 0){
                misCompras = this.subastaFacade.findByCategoriaMax(cat,max);
            }
        }

        request.setAttribute("subastas", misCompras);
        request.getRequestDispatcher("misProductos.jsp").forward(request, response);

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
