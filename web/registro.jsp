<%-- 
    Document   : registro
    Created on : 12-abr-2022, 13:12:09
    Author     : Usuario
--%>

<%@page import="entity.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <%
        

        Usuario usuario = (Usuario)request.getAttribute("usuario");
    %>  
    <body>
                  
        <h1>Datos del cliente</h1>
        <form method="POST" action="servletGuardar">
            <input type="hidden" name="id" value="" />
            Nombre: <input type="text" size="30" name="nombre" value="" /> <br/>
            Apellidos: <input type="text" size="30" name="apellidos" value="" /> <br/>
            Sexo:
           <div>
            <input type="radio" id="Masculino" name="sexo" value="H"checked>
            <label for="Masculino">Masculino</label>
            </div>

            <div>
            <input type="radio" id="Femenino" name="sexo" value="M"
            checked>
            <label for="Femenino">Femenino</label>
            </div>
            Edad: <input type="number" size="30" name="edad" value="" /> <br/>
            Email:<input type="text" size="40" name="email" value="" /> <br/>
            Contraseña:<input type="password" size="40" name="password" value="" /> <br/>
            Domicilio: <input type="text" size="30" name="domicilio" value="" /> <br/>
            Ciudad: <input type="text" size="25" name="ciudad" value="" /> <br/>
            <input type="submit" value="Enviar" />
        </form>
    </body>
</html>
