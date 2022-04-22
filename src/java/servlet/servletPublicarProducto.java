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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 *
 * @author Usuario
 */
public class servletPublicarProducto extends HttpServlet {
 @EJB ProductoFacade productoFC;
 @EJB UsuarioFacade usuarioFC;
 @EJB SubastaFacade subastaFC;

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
         response.setContentType("text/html;charset=UTF-8");
   
        String str;
        Integer aux;
        Subasta subasta = new Subasta();
        Producto producto = new Producto();
        
        str = request.getParameter("id");
        Usuario user = usuarioFC.find(Integer.parseInt(str));
        
        str = request.getParameter("titulo");
        producto.setTitulo(str);
        str = request.getParameter("descripcion");
        producto.setDescripcion(str);
        str = request.getParameter("categoria");
        producto.setCategoria(str);
        str = request.getParameter("foto");
        producto.setUrlFoto(str);
        producto.setEstado("En venta");
        productoFC.create(producto);
        
        
        
        
        Date date = new Date(System.currentTimeMillis());
        subasta.setApertura(date);
        
        
      
        str = request.getParameter("puja_inicial");
        subasta.setPrecioInicial(Double.parseDouble(str));
        subasta.setPujaMaxima(Double.parseDouble(str));
        
        subasta.setVendedor(user);
        subasta.setProducto(producto);
        subastaFC.create(subasta);
        
        producto.setSubasta(subasta);
        productoFC.edit(producto);
        
        response.sendRedirect(request.getContextPath()+"/servletListadoSubastas"); 
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