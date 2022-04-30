/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dao.ProductoFacade;
import dao.UsuarioFacade;
import entity.Producto;
import entity.Subasta;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.SubastaService;

/**
 *
 * @author X430F
 */
public class servletAdminModificarProducto extends HttpServlet {
    @EJB UsuarioFacade usuarioFC;
    @EJB ProductoFacade productoFC;
    @EJB SubastaService subastaService;

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
        String str;
        Integer aux;
        
        str = request.getParameter("id");
        Subasta subasta = this.subastaService.buscarSubasta(Integer.parseInt(str));
        Producto producto = subasta.getProducto();
        
        
       /*
        str = request.getParameter("fecha_cierre");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date=new Date();
     try {
         date = formatter.parse(str);
     } catch (ParseException ex) {
         Logger.getLogger(servletPublicarProducto.class.getName()).log(Level.SEVERE, null, ex);
     }
        subasta.setCierre(date);
        */
        str = request.getParameter("titulo");
        producto.setTitulo(str);
        str = request.getParameter("descripcion");
        producto.setDescripcion(str);
        str = request.getParameter("categoria");
        producto.setCategoria(str);
        str = request.getParameter("foto");
        producto.setUrlFoto(str);

        this.productoFC.edit(producto);
        
        response.sendRedirect(request.getContextPath()+"/servletAdmin");
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
