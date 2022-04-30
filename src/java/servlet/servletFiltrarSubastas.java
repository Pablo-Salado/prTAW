package servlet;

import dao.ProductoFacade;
import dao.UsuarioFacade;
import entity.Producto;
import entity.Subasta;
import entity.Usuario;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.SubastaService;

/**
 *
 * @author Gorpax
 */
public class servletFiltrarSubastas extends TAWServlet {
    @EJB SubastaService subastaService;
    @EJB UsuarioFacade usuarioFacade;
    @EJB ProductoFacade productoFacade;
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
            /*
           Como añadir a priori el que muestre los favoritos:
           Primero hacemos un request parameteter que sea un bool del check
           Hacemos la lista de productos y una lista de subastas auxiliar
           Segundo hacemos un if antes que los filtros acuales de si check == true -> entonces el if es igual pero despues de subasta.findall por ejemplo
           recorremos la lista de productos y la de subastas si el sub.getproducto == producto -> entonces aux.add(sub)
           Al final hacemos subastas = aux;
           
           */
         String str = request.getParameter("usuario");
         Usuario user = this.usuarioFacade.find(Integer.parseInt(str));   
            
         String fav = request.getParameter("filtroFavorito");
         String min = request.getParameter("minPrice");
        String max = request.getParameter("maxPrice");
        String cat = request.getParameter("categoria");
        String nombre = request.getParameter("nombreSubasta");
        List<Subasta> subastas = new ArrayList<Subasta>();
        List<Producto> productos = new ArrayList<Producto>();
        List<Integer> idPro = this.productoFacade.productosFavoritos( user);
        for(Integer i: idPro){
            Producto pro = this.productoFacade.find(i);
            if(!productos.contains(pro)){
                productos.add(pro);
            }
        }
        
        subastas = this.subastaService.filtrarSubastasFavoritas(cat, min, max, nombre, fav, productos);
        
        
        
        
       request.setAttribute("productos", productos);
        request.setAttribute("subastas", subastas);
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