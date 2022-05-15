/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.Marketing;

import dao.UsuarioFacade;
import dto.UsuarioDTO;
import entity.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import service.UsuarioService;
import servlet.TAWServlet;

/**
 *
 * @author Javier Santiburcio
 */
@WebServlet(name = "servletMarketing", urlPatterns = {"/servletMarketing"})
public class servletMarketing extends TAWServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @EJB 
    private UsuarioService usuarioService;
    
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
        if (comprobarMarketing(request, response)) {
        String strUsuario, accion = "todos";
        UsuarioDTO usuario;
        List<Usuario> usuarios;
        List<UsuarioDTO> usuariosDTO = new ArrayList<>();
        
        String goTo = "marketing.jsp";
        
        HttpSession session = request.getSession();
        usuario = (UsuarioDTO) session.getAttribute("usuario");
        
        //usuario = usuarioService.buscarUsuario(Integer.parseInt(strUsuario));
        usuarios = usuarioService.getUsuariosCompradores();
        for(Usuario u : usuarios){
            usuariosDTO.add(u.toDTO());
        }
        
        //request.setAttribute("usuario", usuario);
        request.setAttribute("usuarios", usuariosDTO);
        request.setAttribute("accion", accion);
        
        RequestDispatcher rd = request.getRequestDispatcher(goTo);
        rd.forward(request, response);
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
