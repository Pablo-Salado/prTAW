/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import entity.Lista;
import entity.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
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
 * @author Javier Santiburcio
 */
@WebServlet(name = "servletFiltrarCompradores", urlPatterns = {"/servletFiltrarCompradores"})
public class servletFiltrarCompradores extends TAWServlet {

    @EJB UsuarioService usuarioService;
    
    @EJB ListaService listaService;
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
        String nombre, apellidos, sexo, email, domicilio, ciudad_residencia, minEdad,  maxEdad, tipo_usuario, saldo, accion = "", strUsuario, idLista, titulo, descripcion;
        Usuario usuario;
        Lista lista;
        List<Usuario> usuarios;
        String goTo = "marketing.jsp";
        
        
        nombre = request.getParameter("nombre");
        apellidos = request.getParameter("apellidos");
        sexo = request.getParameter("sexo");
        email = request.getParameter("email");
        domicilio = request.getParameter("domicilio");
        ciudad_residencia = request.getParameter("ciudad_residencia");
        minEdad = request.getParameter("minEdad");
        maxEdad = request.getParameter("maxEdad");
        tipo_usuario = request.getParameter("tipo_usuario");
        saldo = request.getParameter("saldo");
        
        accion = request.getParameter("accion");
        
        HttpSession session = request.getSession();
        usuario = (Usuario) session.getAttribute("usuario");
        //strUsuario = request.getParameter("usuario");
        
        //usuario = usuarioService.buscarUsuario(Integer.parseInt(strUsuario)); 
        
        if(accion == null){
            accion = "";
        }
        
        if(accion.compareTo("filtrar") == 0){ // SI ESTAMOS FILTRANDO
            usuarios = usuarioService.filtrarCompradores2(nombre,  apellidos, sexo, email, 
                domicilio, ciudad_residencia, minEdad,  maxEdad, tipo_usuario, saldo);
            request.setAttribute("usuarios", usuarios);
            request.setAttribute("accion", "filtrar");
            
        }else if (accion.compareTo("guardar") == 0){ // SI QUEREMOS GUARDAR FILTRO
            goTo = "crearLista.jsp";
            request.setAttribute("accion", accion);
        }else if(accion.compareTo("editar") == 0){
           idLista = request.getParameter("idLista");
           
           lista = listaService.buscar(Integer.parseInt(idLista));
           titulo = lista.getNombre();
           descripcion = lista.getDescripcion();
           String[] atribs = lista.getAtributos().split(",");
           nombre = atribs[0];
            apellidos = atribs[1];
            sexo = atribs[2];
            email = atribs[3];
            domicilio = atribs[4];
            ciudad_residencia = atribs[5];
            minEdad = atribs[6]; 
            maxEdad = atribs[7]; 
            tipo_usuario= atribs[8];
            if(atribs.length < 10){
                saldo = "";
            }else{
                saldo= atribs[9];
            }
           
           request.setAttribute("accion", accion);
           request.setAttribute("idLista", idLista);
           request.setAttribute("titulo", titulo);
           request.setAttribute("descripcion", descripcion);
                      
           
           goTo = "crearLista.jsp";
        }else{ // Si llegamos desde mis listas 
            idLista = request.getParameter("idLista");
            
            lista = listaService.buscar(Integer.parseInt(idLista));
            
            String[] atribs = lista.getAtributos().split(",");
            nombre = atribs[0];
            apellidos = atribs[1];
            sexo = atribs[2];
            email = atribs[3];
            domicilio = atribs[4];
            ciudad_residencia = atribs[5];
            minEdad = atribs[6]; 
            maxEdad = atribs[7]; 
            tipo_usuario= atribs[8];
            if(atribs.length < 10){
                saldo = "";
            }else{
                saldo= atribs[9];
            }
            
            usuarios = usuarioService.filtrarCompradores2(nombre,  apellidos, sexo, email, 
                domicilio, ciudad_residencia, minEdad,  maxEdad, tipo_usuario, saldo);
            
            request.setAttribute("usuarios", usuarios);
            goTo = "lista.jsp";
            
        }
        
        request.setAttribute("nombre",nombre);
        request.setAttribute("apellidos",apellidos);
        request.setAttribute("sexo",sexo);
        request.setAttribute("email",email);
        request.setAttribute("domicilio",domicilio);
        request.setAttribute("ciudad_residencia",ciudad_residencia);
        request.setAttribute("minEdad",minEdad);
        request.setAttribute("maxEdad",maxEdad);
        request.setAttribute("tipo_usuario",tipo_usuario);
        request.setAttribute("saldo",saldo);
        
        request.setAttribute("usuario", usuario);
        
        
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
