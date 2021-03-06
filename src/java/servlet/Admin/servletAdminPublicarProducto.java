/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.Admin;

import dto.ProductoDTO;
import dto.SubastaDTO;
import dto.UsuarioDTO;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.ProductoService;
import service.SubastaService;
import service.UsuarioService;

/**
 *
 * @author Pablo Salado
 */
public class servletAdminPublicarProducto extends HttpServlet {
    @EJB ProductoService productoService;
    @EJB UsuarioService usuarioService;
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
        
        String id = request.getParameter("id");
        UsuarioDTO user = this.usuarioService.buscarUsuario(Integer.parseInt(id));
        
        String titulo = request.getParameter("titulo");
        String descripcion = request.getParameter("descripcion");
        String categoria = request.getParameter("categoria");
        String url = request.getParameter("foto");
        this.productoService.crearProducto(titulo, descripcion, url, "En venta", categoria, null);
        
        
        Date date = new Date(System.currentTimeMillis());
        String puja = request.getParameter("puja_inicial");
        List<ProductoDTO> pro = this.productoService.listarProductos();
        this.subastaService.crearSubasta(date, null,Double.parseDouble(puja), Double.parseDouble(puja), user.getIdUsuario(), pro.get(pro.size()-1).getIdPRODUCTO());

        List<SubastaDTO> sub = this.subastaService.listarSubastas();
        this.productoService.modificarSubasta(pro.get(pro.size()-1).getIdPRODUCTO(), sub.get(sub.size()-1).getIdSUBASTA());
        
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
