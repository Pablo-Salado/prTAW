/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dao.NotificacionesFacade;
import dao.ProductoFacade;
import dao.PujadoresFacade;
import dao.UsuarioFacade;
import entity.Notificaciones;
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
import service.SubastaService;

/**
 *
 * @author Usuario
 */
public class servletTerminarSubasta extends HttpServlet {
@EJB SubastaService subastaService;
@EJB ProductoFacade productoFC;
@EJB UsuarioFacade usuarioFC;
@EJB PujadoresFacade pujadorFC;
@EJB NotificacionesFacade notiFC;
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
        Subasta subasta = this.subastaService.buscarSubasta(Integer.parseInt(str));
        Producto producto = subasta.getProducto();
        str = request.getParameter("id");
        Usuario user = this.usuarioFC.find(Integer.parseInt(str));
        
        Date date = new Date(System.currentTimeMillis());
        subasta.setCierre(date);
        
        List<Integer> aux = this.pujadorFC.getPujadores(subasta);
        if(aux.isEmpty()){
        producto.setEstado("No vendido");
        }else{
        
        Usuario ganador = this.usuarioFC.find(this.pujadorFC.getPujadorMaximo(subasta));
        producto.setEstado("Vendido");
        subasta.setComprador(user);
        
        List<Usuario> notificados = new ArrayList<Usuario>();
        for(Integer i: aux){
           Usuario usuarioAnyadir = this.usuarioFC.find(i);
           if(!notificados.contains(usuarioAnyadir)){
               notificados.add(this.usuarioFC.find(i));
           }  
        }
        for(Usuario u : notificados){
            Notificaciones not = new Notificaciones();
            not.setIdSubasta(subasta);
            not.setIdUsuario(u);
            if(u.getIdUSUARIO() == ganador.getIdUSUARIO()){
                not.setGanador("ha sido el ganador");
            }else{
                not.setGanador("NO ha sido el ganador");
            }
            this.notiFC.create(not);
        }
         
        }
        this.productoFC.edit(producto);
        
        date = new Date(System.currentTimeMillis());
        
        this.subastaService.modificarFechaCierre(subasta.getIdSUBASTA(), date);
        
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
