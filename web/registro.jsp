<%-- 
    Document   : registro
    Created on : 12-abr-2022, 13:12:09
    Author     : Usuario
--%>

<%@page import="dto.UsuarioDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registrarse</title>
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
        <link href="signup.css" rel="stylesheet">
    </head>
    <%
        

        UsuarioDTO usuario = (UsuarioDTO)request.getAttribute("usuario");
    %>  
    <body class = "text-center">
        <main class = "form-signin">
            <form method="POST" action="servletGuardar">
                <h1 class="h3 mb-3 fw-normal">¿Nuevo cliente? Por aquí, por favor:</h1>
                <input type="hidden" name="id" value="" />
                <input type="hidden" name="tipo" value="CV"/>
                <div class="form-floating">
                    <input type="text" class="form-control" size="30" id="floatingName" placeholder="Nombre" name="nombre">
                    <label for="floatingName">Nombre</label>
                </div>
                <div class="form-floating">
                    <input type="text" class="form-control" size="30" id="floatingSurname" placeholder="Apellidos" name="apellidos">
                    <label for="floatingSurname">Apellidos</label>
                </div>
                Sexo:
               <div>
                    <input type="radio" id="Masculino" name="sexo" value="H"checked>
                    <label for="Masculino">Masculino</label>
                </div>

                <div>
                    <input type="radio" id="Femenino" name="sexo" value="M" checked>
                    <label for="Femenino">Femenino</label>
                </div>
                <div class="form-floating">
                    <input type="number" class="form-control" size="30" id="floatingEdad" placeholder="Edad" name="edad">
                    <label for="floatingEdad">Edad</label>
                </div>
                <div class="form-floating">
                    <input type="text" class="form-control" size="30" id="floatingInput" placeholder="Email" name="email">
                    <label for="floatingInput">Email</label>
                </div>
                <div class="form-floating">
                    <input type="password" class="form-control" size="30" id="floatingPassword" placeholder="Contraseña" name="password">
                    <label for="floatingPassword">Contraseña</label>
                </div>
                <div class="form-floating">
                    <input type="text" class="form-control" size="30" id="floatingDomicilio" placeholder="Domicilio" name="domicilio">
                    <label for="floatingDomicilio">Domicilio</label>
                </div>
                <div class="form-floating">
                    <input type="text" class="form-control" size="30" id="floatingCiudad" placeholder="Ciudad" name="ciudad">
                    <label for="floatingCiudad">Ciudad</label>
                </div>
                <button class="w-60 btn btn-lg btn-primary" type="submit" value="Enviar">Continuar</button>
            </form>
        </main>
    </body>
</html>
