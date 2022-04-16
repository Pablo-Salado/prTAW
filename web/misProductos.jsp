<%-- 
    Document   : misProductos
    Created on : 15-abr-2022, 12:38:41
    Author     : Usuario
--%>

<%@page import="entity.Usuario"%>
<%@page import="java.util.List"%>
<%@page import="entity.Subasta"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <%Usuario user = (Usuario)session.getAttribute("usuario");%>
    <body>
       <td><a href="servletListadoSubastas">Volver inicio</a></td>    

       <table border="1">
            <tr>
                <th>TITULO</th>
                <th>DESCRIPCION</th>
                <th>FOTO</th>
                <th>VENDEDOR</th>
                <th>APERTURA</th>
                <th>CIERRE</th>  
                <th>PUJA-INICIAL</th>    
                <th>PUJA-ACTUAL</th>
                <th>ESTADO</th>
                
                
            </tr>
        <%
            List<Subasta> subastas = (List)request.getAttribute("subastas");
            for (Subasta sub :subastas) {
                if(sub.getVendedor().getIdUSUARIO()==user.getIdUSUARIO()){
               
        %>    
        <tr>
            <td><%= sub.getProducto().getTitulo() %></td>   
            <td><%= sub.getProducto().getDescripcion() %></td>   
            <td><img src<%=sub.getProducto().getUrlFoto()%>    ></td>   
            <td><%= sub.getVendedor().getNombre() %></td> 
            <td><%= sub.getApertura() %></td>
            <td><%= sub.getCierre() %></td>
            <td><%= sub.getPujaMaxima() %></td> 
            <td><%= sub.getPrecioInicial() %></td>       
            
            <td><%= sub.getPujaMaxima() %></td> 
            <td><%= sub.getProducto().getEstado() %></td> 
            <td><a href="servletBorrarSubasta?subasta=<%= sub.getIdSUBASTA() %>">Borrar</a></td> 
            <td><a href="servletAccesoModificarProducto?subasta=<%= sub.getIdSUBASTA() %>&id=<%=user.getIdUSUARIO()%>">Modificar</a></td>   
            <td><a href="servletTerminarSubasta?subasta=<%= sub.getIdSUBASTA() %>">Terminar subasta</a></td>   
            
        
                           
                    
        </tr>
        <%
            }
            }
        %>
    
    </body>
</html>
