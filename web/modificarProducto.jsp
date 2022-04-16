<%-- 
    Document   : modificarProducto
    Created on : 15-abr-2022, 23:12:53
    Author     : Usuario
--%>

<%@page import="entity.Subasta"%>
<%@page import="entity.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
   <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>publicarProducto</title>
    </head>
     <%
        

    Usuario user = (Usuario)session.getAttribute("usuario");
     if (user == null) {
        response.sendRedirect(request.getContextPath());
    } 

       
       
    %>  
    <body>
        <h1>Datos del producto</h1>
        <form method="POST" action="servletModificarProducto">
            <input type="hidden" name="id" value="<%=user.getIdUSUARIO()%>" />
 
            Titulo: <input type="text" size="30" name="titulo" value="" /> <br/>
            Descripcion: <input type="text" size="200" name="descripcion" value="" /> <br/>
            Precio inicial: <input type="text" size="30" name="puja_inicial" value="" /> <br/>
            
            Foto:<input type="text" size="30" name="foto" value="" /> <br/>
            Categoria:<select name ="categoria">
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

