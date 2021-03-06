/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dto.ProductoDTO;
import dto.SubastaDTO;
import dto.UsuarioDTO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import service.ProductoService;
import service.SubastaService;
import service.UsuarioService;

/**
 *
 * @author Alejandro Medina
 */
public class servletFavorito extends TAWServlet {
    @EJB SubastaService subastaService;
    @EJB UsuarioService usuarioService;
    @EJB ProductoService productoService;
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
        if(super.comprobarSession(request, response)){
            
         
        String str = request.getParameter("usuario");
        UsuarioDTO user = this.usuarioService.buscarUsuario(Integer.parseInt(str));
       
        str = request.getParameter("subasta");
        SubastaDTO sub = this.subastaService.buscarSubasta(Integer.parseInt(str));
        
        String chck = request.getParameter("favorito");
        
        ProductoDTO pro = sub.getProducto();
        
        List<SubastaDTO> subastas = this.subastaService.listarSubastas();
        
        List<ProductoDTO> productos = new ArrayList<ProductoDTO>();
        List<Integer> idPro = this.productoService.listaFavoritos(user.getIdUsuario());
        for(Integer i: idPro){
            ProductoDTO aux = this.productoService.buscarProducto(i);
            if(!productos.contains(aux)){
                productos.add(aux);
            }
        }
        if(chck == null){
            this.productoService.eliminarProductoFavorito(pro.getIdPRODUCTO(), user.getIdUsuario());
            productos.removeIf(pr -> (pr.getIdPRODUCTO() == pro.getIdPRODUCTO()));
        }else{
            this.productoService.addProductoFavorito(pro.getIdPRODUCTO(), user.getIdUsuario());
            productos.add(pro);
        }
        
       
        request.setAttribute("subastas", subastas);  
        HttpSession session = request.getSession();
        session.setAttribute("productos", productos);
        request.getRequestDispatcher("subastas.jsp").forward(request, response);        
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
