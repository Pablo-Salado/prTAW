/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.Comprador;



import dto.SubastaDTO;
import dto.UsuarioDTO;
import entity.Subasta;
import entity.Usuario;
import java.io.IOException;
import java.util.Date;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import service.PujadoresService;
import service.SubastaService;
import service.UsuarioService;

/**
 *
 * @author Alejandro Medina
 */
public class servletPujar extends HttpServlet {
    @EJB SubastaService subastaService;
    @EJB UsuarioService usuarioService;
    @EJB PujadoresService pujadoresService; 
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
        
        String usuario = request.getParameter("usuario");
        UsuarioDTO user = this.usuarioService.buscarUsuario(Integer.parseInt(usuario));
        
        String subasta = request.getParameter("subasta");
        
        SubastaDTO sub = this.subastaService.buscarSubasta(Integer.parseInt(subasta));
        Date fecha = new Date(System.currentTimeMillis());
        String puja = request.getParameter("puja");
        
        if(puja.equals("")){
             response.sendRedirect(request.getContextPath()+"/servletListadoSubastas"); 
        }else{
             
        Double puja_max = Double.valueOf(puja);
          
        this.pujadoresService.crearPujador(user.getIdUsuario(), sub.getIdSUBASTA(), puja_max, fecha);
       
        this.subastaService.modificarPujaMaxima(Integer.parseInt(subasta), puja_max);
        
        this.usuarioService.restaSaldo(user.getIdUsuario(), user.getSaldo() - puja_max);
        user.setSaldo(user.getSaldo() - puja_max);
        
        HttpSession session = request.getSession();
        session.setAttribute("saldo", user.getSaldo());
        
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
