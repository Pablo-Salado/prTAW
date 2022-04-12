<%-- 
    Document   : login
    Created on : 11-abr-2022, 15:35:46
    Author     : Usuario
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bienvenidos al sistema</title>
        <title>JSP Page</title>
    </head>
    <%
        String strError = (String)request.getAttribute("error");
        if (strError == null) strError = "";
    %> 
    <body>
        <h1>Login</h1>
        <%= strError %>
        <form method="POST" action="servletLogin">
            Usuario: <input type="text" name="usuario" value="" /><br/>
            Clave: <input type="password" name="clave" value="" /><br/> <br/>            
            <input type="submit" value="Enviar" />
        </form>
        <a href="servletRegistro">Registrarse ... </a>
    </body>
</html>
