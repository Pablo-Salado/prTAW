/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dao.ProductoFacade;
import dao.PujadoresFacade;
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
 * @author Usuario
 */
public class servletTerminarSubasta extends HttpServlet {
@EJB SubastaFacade subastaFC;
@EJB ProductoFacade productoFC;
@EJB UsuarioFacade usuarioFC;
@EJB PujadoresFacade pujadorFC;
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
        String str = request.getParameter("subasta");
        Subasta subasta = this.subastaFC.find(Integer.parseInt(str));
        Producto producto = subasta.getProducto();
        str = request.getParameter("id");
        Usuario user = this.usuarioFC.find(Integer.parseInt(str));
        
        Date date = new Date(System.currentTimeMillis());
        subasta.setCierre(date);
        List<Pujadores> pujadores = this.pujadorFC.findAll();
        List<Pujadores> misPujadores = new ArrayList<Pujadores>();
        for(Pujadores p: pujadores){
            if(p.getSubasta().getVendedor().getIdUSUARIO()==user.getIdUSUARIO()){
                misPujadores.add(p);
            }
        }
        
        
        if(misPujadores.isEmpty()){
        producto.setEstado("No vendido");
        }else{
        producto.setEstado("Vendido");

        }
        
        date = new Date(System.currentTimeMillis());
        
        subasta.setCierre(date);
        
        this.subastaFC.edit(subasta);
        this.productoFC.edit(producto);
        response.sendRedirect(request.getContextPath()+"/servletListadoMisProductos");
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
