<%-- 
    Document   : adminPublicarUsuario
    Created on : 10-may-2022, 18:55:18
    Author     : Pablo Salado
--%>

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
        <title>Publicar usuario</title>
    </head>
    <%
        
        Usuario user = (Usuario)session.getAttribute("usuario");
         if (user == null) {
            response.sendRedirect(request.getContextPath());
        } 
         Usuario usuarioModificar = (Usuario) request.getAttribute("usuarioModificar");
       
    %>
    <header>
        <div class="px-3 py-3 bg-dark text-white shadow">
            <div class="container">
                <div class="row justify-content-between">
                    <div class ="col-auto">
                      <a href="servletAdmin" class="text-light ">
                          <svg xmlns="http://www.w3.org/2000/svg" width="40" height="40" fill="currentColor" class="bi bi-shop" viewBox="0 0 16 16">
                                <path d="M2.97 1.35A1 1 0 0 1 3.73 1h8.54a1 1 0 0 1 .76.35l2.609 3.044A1.5 1.5 0 0 1 16 5.37v.255a2.375 2.375 0 0 1-4.25 1.458A2.371 2.371 0 0 1 9.875 8 2.37 2.37 0 0 1 8 7.083 2.37 2.37 0 0 1 6.125 8a2.37 2.37 0 0 1-1.875-.917A2.375 2.375 0 0 1 0 5.625V5.37a1.5 1.5 0 0 1 .361-.976l2.61-3.045zm1.78 4.275a1.375 1.375 0 0 0 2.75 0 .5.5 0 0 1 1 0 1.375 1.375 0 0 0 2.75 0 .5.5 0 0 1 1 0 1.375 1.375 0 1 0 2.75 0V5.37a.5.5 0 0 0-.12-.325L12.27 2H3.73L1.12 5.045A.5.5 0 0 0 1 5.37v.255a1.375 1.375 0 0 0 2.75 0 .5.5 0 0 1 1 0zM1.5 8.5A.5.5 0 0 1 2 9v6h1v-5a1 1 0 0 1 1-1h3a1 1 0 0 1 1 1v5h6V9a.5.5 0 0 1 1 0v6h.5a.5.5 0 0 1 0 1H.5a.5.5 0 0 1 0-1H1V9a.5.5 0 0 1 .5-.5zM4 15h3v-5H4v5zm5-5a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1v3a1 1 0 0 1-1 1h-2a1 1 0 0 1-1-1v-3zm3 0h-2v3h2v-3z"/>
                          </svg> 
                      </a> 
                    </div>
                    <div class="col-auto">
                        <div class ="row justify-content-between">
                            <div class ="col-auto">
                              <div class="dropdown">
                                <button class="btn btn-outline-primary dropdown-toggle" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
                                  <i class="bi bi-person-circle"></i> Admin
                                </button>           
                                  <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                                      <li><a class="dropdown-item" href="servletAdmin">Listar productos</a></li>
                                      <li><a class="dropdown-item" href="servletAdminListarUsuarios">Listar usuarios</a></li>
                                      <li><a class="dropdown-item" href="">Dar de alta a usuarios de marketing</a></li>
                                      <li><a class="dropdown-item" href="servletLogout">Cerrar sesión</a></li>
                                  </ul>
                              </div>
                            </div>
                        </div>  
                    </div>
                </div>
            </div>
        </div>
    </header>
    <body>
        <div class="text-center border-bottom primary">
            <h1 class="text-primary">Datos del usuario </h1>
        </div>
        <%if(usuarioModificar==null){%>
        <form method="POST" action="servletAdminCrearUsuario">
        <%}else{%>
        <form method="POST" action="servletAdminModificarUsuario">
        <%}%>
            <%if(usuarioModificar==null){%>
           <input type="hidden" name="id" value="<%=user.getIdUSUARIO()%>" />
            <%}else{%>
            <input type="hidden" name="id" value="<%=usuarioModificar.getIdUSUARIO()%>" />
            <%}%>
            <div class="container mb-3 col-4">
                
            <div class=" mb-3">
                <label for="exampleFormControlInput1" class="form-label">Nombre</label>
                <input type="text" class="form-control" id="exampleFormControlInput1" name="nombre" value="<%= usuarioModificar==null? "": usuarioModificar.getNombre() %>">
            </div>
            <div class=" mb-3">
                <label for="exampleFormControlInput1" class="form-label">Apellidos</label>
                <input type="text" class="form-control" id="exampleFormControlInput1" name="apellidos" value="<%= usuarioModificar==null? "": usuarioModificar.getEpellidos() %>">
            </div>
            
            <div class=" mb-3">
                <label for="exampleFormControlInput1" class="form-label">Sexo (H o M)</label>
                <input type="text" class="form-control" id="exampleFormControlInput1" name="sexo" value="<%= usuarioModificar==null? "": usuarioModificar.getSexo() %>">
            </div>
            <div class=" mb-3">
                <label for="exampleFormControlInput1" class="form-label">Email</label>
                <input type="text" class="form-control" id="exampleFormControlInput1" name="email" value="<%= usuarioModificar==null? "": usuarioModificar.getEmail() %>">
            </div>
            <div class=" mb-3">
                <label for="exampleFormControlInput1" class="form-label">Contraseña</label>
                <input type="password" class="form-control" id="exampleFormControlInput1" name="password" value="<%= usuarioModificar==null? "": usuarioModificar.getPassword() %>">
            </div>
            <div class=" mb-3">
                <label for="exampleFormControlInput1" class="form-label">Domicilio</label>
                <input type="text" class="form-control" id="exampleFormControlInput1" name="domicilio" value="<%= usuarioModificar==null? "": usuarioModificar.getDomicilio() %>">
            </div>
            <div class=" mb-3">
                <label for="exampleFormControlInput1" class="form-label">Ciudad de residencia</label>
                <input type="text" class="form-control" id="exampleFormControlInput1" name="ciudadResidencia" value="<%= usuarioModificar==null? "": usuarioModificar.getCiudadResidencia() %>">
            </div>           
            <div class=" mb-3">
                <label for="exampleFormControlInput1" class="form-label">Edad</label>
                <input type="text" class="form-control" id="exampleFormControlInput1" name="edad" value="<%= usuarioModificar==null? "": usuarioModificar.getEdad() %>">
            </div>
            <div class=" mb-3">
                <label for="exampleFormControlInput1" class="form-label">Tipo de usuario</label>
                <input type="text" class="form-control" id="exampleFormControlInput1" name="tipoUsuario" value="<%= usuarioModificar==null? "": usuarioModificar.getTipoUsuario() %>">
            </div>
            <div class=" mb-3">
                <label for="exampleFormControlInput1" class="form-label">Saldo</label>
                <input type="text" class="form-control" id="exampleFormControlInput1" name="saldo" value="<%= usuarioModificar==null? "": usuarioModificar.getSaldo() %>">
            </div>
            <input type="submit" value="Enviar" class="mb-3 bt" />
            </div>
        
        
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    </body>
</html>
