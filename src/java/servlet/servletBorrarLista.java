/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dto.ListaDTO;
import entity.Lista;
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
import service.ListaService;
import service.UsuarioService;

/**
 *
 * @author javie
 */
@WebServlet(name = "servletBorrarLista", urlPatterns = {"/servletBorrarLista"})
public class servletBorrarLista extends TAWServlet {
    
    @EJB 
    private ListaService listaService;
    
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
        String idLista, strUsuario;
        Usuario usuario;
        List<Lista> listas;
        List<ListaDTO> listasDTO = new ArrayList<>();
        String goTo = "misListas.jsp";
        
        //strUsuario = request.getParameter("usuario");
        HttpSession session = request.getSession();
        usuario = (Usuario) session.getAttribute("usuario");
        idLista = request.getParameter("idLista");
               
        //usuario = usuarioService.buscarUsuario(Integer.parseInt(strUsuario));
        listas = usuario.getListaList();
        
        listas.remove(listaService.buscar(Integer.parseInt(idLista)));
        listaService.borrar(Integer.parseInt(idLista));
        //listas = listaService.getListasPorUsuario(usuario);
        
        for(Lista l: listas){
            listasDTO.add(l.toDTO());
        }
        
        request.setAttribute("usuario", usuario);
        request.setAttribute("listas", listasDTO);
        
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
