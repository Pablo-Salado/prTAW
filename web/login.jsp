<%-- 
    Document   : login
    Created on : 30-mar-2022, 11:55:39
    Author     : X430F
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bienvenidos al sistema</title>
    </head>
    <%
        String strError = (String) request.getAttribute("error");
        if(strError == null){
            strError = "";
        }
    %>
    <body>
        <h1>Login</h1>
        <%= strError %>
        <form method="POST" action="CustomerLoginServlet">
            Usuario: <input type="text" name="usuario" value=""><br>
            Clave: <input type="password" name="clave" value=""><br>
            <input type="submit" value="Enviar" />
            
        </form>
    </body>
</html>