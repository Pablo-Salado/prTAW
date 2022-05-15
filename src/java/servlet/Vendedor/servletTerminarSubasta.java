/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.Vendedor;

import dto.ProductoDTO;
import dto.SubastaDTO;
import dto.UsuarioDTO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.NotificacionesService;
import service.ProductoService;
import service.PujadoresService;
import service.SubastaService;
import service.UsuarioService;

/**
 *
 * @author Miguel Angel Cosano Ramirez
 */
public class servletTerminarSubasta extends HttpServlet {
@EJB SubastaService subastaService;
@EJB ProductoService productoService;
@EJB UsuarioService usuarioService;
@EJB PujadoresService pujadorService;
@EJB NotificacionesService notificacionesService;
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
        String subasta = request.getParameter("subasta");
        SubastaDTO sub = this.subastaService.buscarSubasta(Integer.parseInt(subasta));
        ProductoDTO producto = sub.getProducto();
        String str = request.getParameter("id");
        UsuarioDTO user = this.usuarioService.buscarUsuario(Integer.parseInt(str));

        List<Integer> aux = this.pujadorService.buscarPujadoresSubasta(sub.getIdSUBASTA());
        if(aux.isEmpty()){
        this.productoService.modificarEstado(producto.getIdPRODUCTO(), "No vendido");
        }else{
        
        UsuarioDTO ganador = this.usuarioService.buscarUsuario(this.pujadorService.buscarPujadorMaximo(sub.getIdSUBASTA()));
        this.productoService.modificarEstado(producto.getIdPRODUCTO(), "Vendido");
        this.subastaService.modificarComprador(Integer.parseInt(subasta), ganador.getIdUsuario());
        
        List<UsuarioDTO> notificados = new ArrayList<UsuarioDTO>();
        for(Integer i: aux){
           UsuarioDTO usuarioAnyadir = this.usuarioService.buscarUsuario(i);
           if(!notificados.contains(usuarioAnyadir)){
               notificados.add(this.usuarioService.buscarUsuario(i));
           }  
        }
        for(UsuarioDTO u : notificados){
            if(u.getIdUsuario() == ganador.getIdUsuario()){
                this.notificacionesService.crearSubasta(u.getIdUsuario(), sub.getIdSUBASTA(), "ha sido el ganador");
            }else{
               this.notificacionesService.crearSubasta(u.getIdUsuario(), sub.getIdSUBASTA(), "NO ha sido el ganador");
            }

        }
         
        }
        
        Date date = new Date(System.currentTimeMillis());
        
        this.subastaService.modificarFechaCierre(sub.getIdSUBASTA(), date);
        
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
