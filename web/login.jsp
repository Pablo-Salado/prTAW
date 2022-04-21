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
        <title>Inicia sesión</title>
        
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">
        
        <style>
            .bd-placeholder-img {
                font-size: 1.125rem;
                text-anchor: middle;
                -webkit-user-select: none;
                -moz-user-select: none;
                user-select: none;
            }

            @media (min-width: 768px) {
                .bd-placeholder-img-lg {
                font-size: 3.5rem;
                }
            }
        </style>

    
        <!-- Custom styles for this template -->
        <link href="signin.css" rel="stylesheet">
    </head>
    <%
        String strError = (String)request.getAttribute("error");
        if (strError == null) strError = "";
    %> 
    <body class = "text-center">
        <main class ="form-signin">
            <%= strError %>
            <form method="POST" action="servletLogin">
                <svg xmlns="http://www.w3.org/2000/svg" width="57" height="57" fill="currentColor" class="bi bi-bootstrap-fill" viewBox="0 0 16 16">
                   <path d="M6.375 7.125V4.658h1.78c.973 0 1.542.457 1.542 1.237 0 .802-.604 1.23-1.764 1.23H6.375zm0 3.762h1.898c1.184 0 1.81-.48 1.81-1.377 0-.885-.65-1.348-1.886-1.348H6.375v2.725z"/>
                   <path d="M4.002 0a4 4 0 0 0-4 4v8a4 4 0 0 0 4 4h8a4 4 0 0 0 4-4V4a4 4 0 0 0-4-4h-8zm1.06 12V3.545h3.399c1.587 0 2.543.809 2.543 2.11 0 .884-.65 1.675-1.483 1.816v.1c1.143.117 1.904.931 1.904 2.033 0 1.488-1.084 2.396-2.888 2.396H5.062z"/>
                </svg>
                
                <h1 class="h3 mb-3 fw-normal">Iniciar sesión</h1>
                
                <div class="form-floating">
                    <input type="text" class="form-control" id="floatingInput" placeholder="Usuario" name="usuario">
                    <label for="floatingInput">Usuario</label>
                </div>
                <div class="form-floating">
                    <input type="password" class="form-control" id="floatingPassword" placeholder="Clave" name="clave">
                    <label for="floatingPassword">Clave</label>
                </div>
                <div class="checkbox mb-3">
                    <a href="servletRegistro">Registrarse</a>
                </div>
                <button class="w-100 btn btn-lg btn-primary" type="submit" value="Enviar">Iniciar sesión</button>                 
                <p class="mt-5 mb-3 text-muted">&copy; 2022</p>
            </form>
        </main>
    </body>
</html>
