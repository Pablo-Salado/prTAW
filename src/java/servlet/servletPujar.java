/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dao.PujadoresFacade;
import dao.SubastaFacade;
import dao.UsuarioFacade;
import entity.Pujadores;
import entity.Subasta;
import entity.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Gorpax
 */
public class servletPujar extends HttpServlet {
    @EJB SubastaFacade subastaFacade;
    @EJB UsuarioFacade usuarioFacade;
    @EJB PujadoresFacade pujadoresFacade;
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
        
        String str = request.getParameter("usuario");
        Usuario user = this.usuarioFacade.find(Integer.parseInt(str));
        
        str = request.getParameter("subasta");
        
        Subasta sub = this.subastaFacade.find(Integer.parseInt(str));
        Date fecha = new Date(System.currentTimeMillis());
        str = request.getParameter("puja");
        
        if(str.equals("1")){
             response.sendRedirect(request.getContextPath()+"/servletListadoSubastas"); 
        }else{
             
        Double puja = Double.valueOf(str);
        
        Pujadores pj = new Pujadores();
        pj.setFecha(fecha);
        pj.setSubasta(sub);
        pj.setUsuario(user);
        pj.setValorPuja(puja);
        
        this.pujadoresFacade.create(pj);
       
        sub.setPujaMaxima(puja);
        this.subastaFacade.edit(sub);
        
        response.sendRedirect(request.getContextPath()+"/servletListadoSubastas"); 
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
