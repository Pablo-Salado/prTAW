/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.Marketing;

import dao.UsuarioFacade;
import dto.ListaDTO;
import dto.UsuarioDTO;
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
import servlet.TAWServlet;

/**
 *
 * @author Javier Santiburcio
 */
@WebServlet(name = "servletGuardarLista", urlPatterns = {"/servletGuardarLista"})
public class servletGuardarLista extends TAWServlet {

    @EJB
    private ListaService listaService;
    
    @EJB 
    private UsuarioService usuarioService;
    
    @EJB 
    private UsuarioFacade usuarioFacade;
    
    
    
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
        String nombre, apellidos, sexo, email, domicilio, ciudad_residencia, minEdad,  maxEdad, tipo_usuario, saldo, titulo, descripcion, atr, strUsuario, idLista, accion;
        UsuarioDTO usuario;
        List<Lista> listas;
        List<ListaDTO> listasDTO = new ArrayList<>();
        ListaDTO nueva;
        String goTo = "misListas.jsp";
        
        //Recogemos parametros del formulario
        titulo = request.getParameter("titulo");
        descripcion = request.getParameter("descripcion");
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
        
        //Recogemos el id y la accion
        idLista = request.getParameter("idLista");
        accion = request.getParameter("accion");
        
        
        //Recogemos el usuario de la sesion
        HttpSession session = request.getSession();
        usuario = (UsuarioDTO) session.getAttribute("usuario");
        
        if(sexo.compareTo("Hombre")==0){
            sexo = "M";
        }else if(sexo.compareTo("Mujer")==0){
            sexo = "H";
        }
        
        //Formamos el atributo
        atr = nombre + "," + apellidos + "," + sexo + "," + email + "," + domicilio + "," + ciudad_residencia
            + "," + minEdad + "," + maxEdad + "," + tipo_usuario + "," + saldo + ",";
        
        
        
        
        //Creamos la nueva lista y le añadimos los atributos
        nueva = new ListaDTO();
        
        nueva.setNombre(titulo);
        nueva.setDescripcion(descripcion);
        Usuario user = usuarioFacade.find(usuario.getIdUsuario());
        nueva.setUsuario(user);
        nueva.setAtributos(atr);
        
        
        if(accion.compareTo("editar") == 0){ // SI ESTAMOS EDITANDO
            //ListaDTO antigua = listaService.buscar(Integer.parseInt(idLista)); 
            //listasDTO.remove(antigua); // Eliminamos la antigua de la lista
            
            nueva.setIdLISTA(Integer.parseInt(idLista));
            nueva.setNombre(titulo);
            nueva.setDescripcion(descripcion);
            nueva.setUsuario(user);
            nueva.setAtributos(atr);
            
            listaService.editar(nueva); // La editamos en la bd
        }else{
            listaService.crear(nueva);
        }   
        
        listasDTO = usuario.getListaList();
        listasDTO.add(nueva);
        
        //listas = usuario.getListaList();
        //listas = listaService.getListasPorUsuario(usuario);
        
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
