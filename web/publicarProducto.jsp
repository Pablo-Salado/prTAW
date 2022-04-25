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
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="style.css" rel="stylesheet">
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">
    <title>Publicar producto</title>
  </head>
     <%
        

    Usuario user = (Usuario)session.getAttribute("usuario");
     if (user == null) {
        response.sendRedirect(request.getContextPath());
    } 
     Subasta sub = (Subasta)request.getAttribute("subasta");
       
       
    %>  
    <header>
        <div class="px-3 py-0 bg-dark text-white shadow">
          <div class="container">
            <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-md-between py-3">
              <a href="servletListadoSubastas" class="d-flex align-items-center col-md-3 mb-2 mb-md-0 text-dark text-decoration-none">
                <svg class="bi d-block mx-auto mb-1" xmlns="http://www.w3.org/2000/svg" width="40" height="32" fill="white" class="bi bi-bootstrap" viewBox="0 0 16 16">
                  <path d="M5.062 12h3.475c1.804 0 2.888-.908 2.888-2.396 0-1.102-.761-1.916-1.904-2.034v-.1c.832-.14 1.482-.93 1.482-1.816 0-1.3-.955-2.11-2.542-2.11H5.062V12zm1.313-4.875V4.658h1.78c.973 0 1.542.457 1.542 1.237 0 .802-.604 1.23-1.764 1.23H6.375zm0 3.762V8.162h1.822c1.236 0 1.887.463 1.887 1.348 0 .896-.627 1.377-1.811 1.377H6.375z"/>
                  <path d="M0 4a4 4 0 0 1 4-4h8a4 4 0 0 1 4 4v8a4 4 0 0 1-4 4H4a4 4 0 0 1-4-4V4zm4-3a3 3 0 0 0-3 3v8a3 3 0 0 0 3 3h8a3 3 0 0 0 3-3V4a3 3 0 0 0-3-3H4z"/>
                </svg>
              </a>
              <div class="col-md-3 text-end">
                <div class="dropdown">
                  <button class="btn btn-outline-primary dropdown-toggle" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
                    <i class="bi bi-person-circle"></i> Mi perfil
                  </button>
                  <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                    <li><a class="dropdown-item" href="servletMisCompras">Mis compras</a></li>
                    <li><a class="dropdown-item" href="servletListadoMisProductos">Mis ventas</a></li>
                    <li><a class="dropdown-item" href="servletAccesoModificarProducto">Publicar producto</a></li>
                    <li><a class="dropdown-item" href="servletListarNotificaciones">Notificaciones</a></li>
                    <li><a class="dropdown-item" href="servletLogout">Cerrar sesion</a></li>
                  </ul>
                </div>
              </div>
            </div>
          </div>
        </div>
       
      </header>
    <body>
        <div class="text-center border-bottom primary">
            <h1 class="text-primary">Datos del producto </h1>
        </div>
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
            <div class="container mb-3 col-4">
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
            <select name ="categoria" class="mb-3 form-select" value="<%= sub==null? "": sub.getProducto().getCategoria() %>" style="width: 200px">
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
            <input type="submit" value="Enviar" class="mb-3 bt" />
            </div>
            
                   <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

    </body>
</html>
