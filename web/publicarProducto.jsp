<%-- 
    Document   : publicarProducto
    Created on : 14-abr-2022, 20:55:57
    Author     : Usuario
--%>

<%@page import="entity.Subasta"%>
<%@page import="entity.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>publicarProducto</title>
    </head>
     <%
        

    Usuario user = (Usuario)session.getAttribute("usuario");
     if (user == null) {
        response.sendRedirect(request.getContextPath());
    } 
     Subasta sub = (Subasta)request.getAttribute("subasta");
       
       
    %>  
    <body>
        <h1>Datos del producto</h1>
        <%if(sub==null){%>
        <form method="POST" action="servletPublicarProducto">
        <%}else{%>
        <form method="POST" action="servletModificarProducto">
        <%}%>
            <%if(sub==null){%>
           <input type="hidden" name="id" value="<%=user.getIdUSUARIO()%>" />
            <%}else{%>
            <input type="hidden" name="id" value="<%=sub.getIdSUBASTA()%>" />
            <%}%>
            
 
            Titulo: <input type="text" size="30" name="titulo" value="<%= sub==null? "": sub.getProducto().getTitulo() %>" /> <br/>
            Descripcion: <input type="text" size="200" name="descripcion" value="<%= sub==null? "": sub.getProducto().getDescripcion() %>" /> <br/>
            Precio inicial: <input type="text" size="30" name="puja_inicial" value="<%= sub==null? "": sub.getPrecioInicial() %>" /> <br/>
            
            Foto:<input type="text" size="30" name="foto" value="<%= sub==null? "": sub.getProducto().getUrlFoto() %>" /> <br/>
            Categoria:<select name ="categoria" value="<%= sub==null? "": sub.getProducto().getCategoria() %>">
            <option value="MOTOR">Motor</option>
            <option value="DEPORTE">Deporte</option>
            <option value="HOGAR">Hogar</option>
            <option value="INFORMATICA">Informatica</option>
            <option value="IMAGEN Y SONIDO">Imagen y sonido</option>
            <option value="TELEFONIA">Telefonia</option>
            <option value="MODA">Moda</option>
            <option value="JUEGOS">Juegos</option>
            <option value="AFICIONES Y OCIO">Aficiones y ocio</option>
            <option value="OTROS">Otros</option>
            </select><br/>
            <input type="submit" value="Enviar" />
    </body>
</html>
