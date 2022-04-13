<%-- 
    Document   : cabezera
    Created on : 13-abr-2022, 12:08:08
    Author     : Usuario
--%>

<%@page import="java.util.List"%>
<%@page import="javax.ejb.EJB"%>
<%@page import="entity.TipoUsuario"%>
<%@page import="entity.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
@EJB TipoUsuario tiposEJB;
<%
    
    Usuario user = (Usuario)session.getAttribute("usuario");
    TipoUsuario tipo = new TipoUsuario();
    
            
    List<TipoUsuario> tipoList =%>tiposEJB    <%
    
    if (user == null) {
        response.sendRedirect(request.getContextPath());
    }
%>
<table width="80%">
    <tr width="80%">
        <td>Bienvenido, <%= user.getEmail() %></td>
        <td>Session ID: <%= session.getId() %></td>
        <td>Session ID: <%= user.getSubastaList1().size() %></td>
        <td><a href="servletListadoSubastas">Listado de subastas</a></td>    
        <%
            if(user.)
            %>

    </tr>
</table>