/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dto.UsuarioDTO;
import entity.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Javier Santiburcio 33%
 *         Pablo Salado 33%
 *         Miguel Angel Cosano 33%
 */
public abstract class TAWServlet extends HttpServlet {

    @Override
    protected abstract void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;
    


    @Override
    protected abstract void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;

    protected boolean comprobarSession (HttpServletRequest request, HttpServletResponse response) 
                throws ServletException, IOException {
        HttpSession session = request.getSession();
        UsuarioDTO user = (UsuarioDTO)session.getAttribute("usuario");
        if (user == null) {
            response.sendRedirect(request.getContextPath());
            return false;
        } else {
            return true;
        }        
        
    }
    
    protected boolean comprobarAdmin (HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException{
        HttpSession session = request.getSession();
        UsuarioDTO user = (UsuarioDTO)session.getAttribute("usuario");
        if (user.getTipoUsuario().equals("ADMINISTRADOR")) {
            return true;
        } else {
            response.sendRedirect(request.getContextPath());
            return false;
        }       
    }
       
    protected boolean comprobarMarketing (HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException{
        HttpSession session = request.getSession();
        Usuario user = (Usuario)session.getAttribute("usuario");
        if (user.getTipoUsuario().equals("MARKETING")) {
            return true;
        } else {
            response.sendRedirect(request.getContextPath());
            return false;
        }       
    }

}
