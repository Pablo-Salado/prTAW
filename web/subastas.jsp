<%-- 
    Document   : subastas
    Created on : 11-abr-2022, 13:44:23
    Author     : Usuario
--%>

<%@page import="java.util.List"%>
<%@page import="entity.Subasta"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
         <jsp:include page="/cabecera.jsp" /> 
      
        <table border="1">
            <tr>
                <th>APERTURA</th>
                <th>PRECIO-INICIAL</th>    
                <th>COMPRADOR</th>
                                                                                
            </tr>
        <%
            List<Subasta> subastas = (List)request.getAttribute("subastas");
            for (Subasta sub :subastas) {
        %>    
        <tr>
            <td><%= sub.getApertura() %></td>
            <td><%= sub.getPrecioInicial() %></td>       
            <td><%= sub.getVendedor().getNombre() %></td>   
        
                           
                    
        </tr>
        <%
            }
        %>
    
    </body>
</html>
