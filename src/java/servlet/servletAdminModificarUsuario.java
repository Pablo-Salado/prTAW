/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import entity.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.UsuarioService;

/**
 *
 * @author X430F
 */
public class servletAdminModificarUsuario extends HttpServlet {
    @EJB UsuarioService usuarioService;

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
        String idUsuario = request.getParameter("id");
        Usuario usuario = this.usuarioService.buscarUsuario(Integer.parseInt(idUsuario));
        
        String nombre = request.getParameter("nombre");
        String apellidos = request.getParameter("apellidos");
        String sexo = request.getParameter("sexo");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String domicilio = request.getParameter("domicilio");
        if(domicilio.equals("null")){
            domicilio = null;
        }
        String ciudad = request.getParameter("ciudadResidencia");
        if(ciudad.equals("null")){
            ciudad = null;
        }
        String edad = request.getParameter("edad");
        Integer edadInt;
        if(edad.equals("null")){
            edadInt = null;
        }else {
            edadInt = Integer.parseInt(edad);
        }
        String tipo = request.getParameter("tipoUsuario");
        String saldo = request.getParameter("saldo");
        Double saldoDouble = Double.parseDouble(saldo);
        
        this.usuarioService.modificarUsuario(usuario, nombre, apellidos, sexo, password, email, domicilio, ciudad, edadInt, tipo, saldoDouble);
        
        response.sendRedirect(request.getContextPath()+"/servletAdminListarUsuarios");
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
