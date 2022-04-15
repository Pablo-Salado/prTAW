<%-- 
    Document   : cabezera
    Created on : 13-abr-2022, 12:08:08
    Author     : Usuario
--%>

<%@page import="java.util.List"%>
<%@page import="javax.ejb.EJB"%>
<%@page import="entity.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
  
    Usuario user = (Usuario)session.getAttribute("usuario");

    
            

    
    if (user == null) {
        response.sendRedirect(request.getContextPath());
    }
%>
<table width="80%">
    <tr width="80%">
        <td>Bienvenido, <%= user.getEmail() %></td>
        <td>Session ID: <%= session.getId() %></td>
        <%if(user.getTipoUsuario().equals("CV")){%>
            
        
        <td><a href="servletAccesoPublicarProducto?id=<%= user.getIdUSUARIO()%>">Publicar producto</a></td>    
        <td><a href="servletListadoMisProductos">Mis productos</a></td>    
        <%}
        %>

    </tr>
</table>