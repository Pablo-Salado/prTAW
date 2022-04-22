<%-- 
    Document   : publicarProducto
    Created on : 14-abr-2022, 20:55:57
    Author     : Usuario
--%>

<%@page import="entity.Subasta"%>
<%@page import="entity.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <div class="text-center border-bottom primary">
            <h1 class="text-primary">Datos del producto </h1>
        </div>
    </head>
     <%
        

    Usuario user = (Usuario)session.getAttribute("usuario");
     if (user == null) {
        response.sendRedirect(request.getContextPath());
    } 
     Subasta sub = (Subasta)request.getAttribute("subasta");
       
       
    %>  
    <body>
        
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
            <div class="container mb-3">
            <div class=" mb-3">
                <label for="exampleFormControlInput1" class="form-label">Titulo</label>
                <input type="text" class="form-control" id="exampleFormControlInput1" name="titulo" value="<%= sub==null? "": sub.getProducto().getTitulo() %>">
            </div>
            <div class=" mb-3">
                <label for="exampleFormControlInput1" class="form-label">Descripcion</label>
                <input type="text" class="form-control" id="exampleFormControlInput1" name="descripcion" value="<%= sub==null? "": sub.getProducto().getDescripcion() %>">
            </div>
            
            <div class=" mb-3">
                <label for="exampleFormControlInput1" class="form-label">Precio inicial</label>
                <input type="text" class="form-control" id="exampleFormControlInput1" name="puja_inicial" value="<%= sub==null? "": sub.getPrecioInicial() %>">
            </div>
            <div class=" mb-3">
                <label for="exampleFormControlInput1" class="form-label">Enlace de la foto</label>
                <input type="text" class="form-control" id="exampleFormControlInput1" name="foto" value="<%= sub==null? "": sub.getProducto().getUrlFoto() %>">
            </div>
            
            Categoria:<br>
            <select name ="categoria" class="mb-3" value="<%= sub==null? "": sub.getProducto().getCategoria() %>">
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
            <input type="submit" value="Enviar" class="mb-3" />
            </div>
    </body>
</html>
