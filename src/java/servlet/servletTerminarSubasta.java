/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import entity.Producto;
import entity.Subasta;
import entity.Usuario;
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
 * @author Usuario
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
        Subasta sub = this.subastaService.buscarSubasta(Integer.parseInt(subasta));
        Producto producto = sub.getProducto();
        String str = request.getParameter("id");
        Usuario user = this.usuarioService.buscarUsuario(Integer.parseInt(str));

        List<Integer> aux = this.pujadorService.buscarPujadoresSubasta(sub);
        if(aux.isEmpty()){
        this.productoService.modificarEstado(producto, "No vendido");
        }else{
        
        Usuario ganador = this.usuarioService.buscarUsuario(this.pujadorService.buscarPujadorMaximo(sub));
        this.productoService.modificarEstado(producto, "Vendido");
        this.subastaService.modificarComprador(Integer.parseInt(subasta), ganador);
        
        List<Usuario> notificados = new ArrayList<Usuario>();
        for(Integer i: aux){
           Usuario usuarioAnyadir = this.usuarioService.buscarUsuario(i);
           if(!notificados.contains(usuarioAnyadir)){
               notificados.add(this.usuarioService.buscarUsuario(i));
           }  
        }
        for(Usuario u : notificados){
            if(u.getIdUSUARIO() == ganador.getIdUSUARIO()){
                this.notificacionesService.crearSubasta(u, sub, "ha sido el ganador");
            }else{
               this.notificacionesService.crearSubasta(u, sub, "NO ha sido el ganador");
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
