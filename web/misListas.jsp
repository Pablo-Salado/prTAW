<%-- 
    Document   : misListas
    Created on : 05-may-2022, 21:51:38
    Author     : Javier Santiburcio
--%>


<%@page import="dto.UsuarioDTO"%>
<%@page import="dto.ListaDTO"%>
<%@page import="entity.Lista"%>
<%@page import="entity.Usuario"%>
<%@page import="java.util.List"%>
<%@page import="entity.Subasta"%>
<%@page import="entity.Subasta"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
     <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="style.css" rel="stylesheet">
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">
        <title>Mis listas</title>
    </head>
    <% UsuarioDTO user = (UsuarioDTO)session.getAttribute("usuario"); %>
    <body>
        <header>
            <div class="px-3 py-3 bg-dark text-white shadow">
          <div class="container">
              <div class="row justify-content-between">
                  <div class ="col-auto">
                      <a href="#" class="text-light ">
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
                                  <i class="bi bi-person-circle"></i> Marketing
                                </button>           
                                <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1" style="width: 300px">
                                      <li><a class="dropdown-item" href="servletListarListas">Mis Listas</a></li>
                                      <li><a class="dropdown-item" href="servletMarketing">Compradores</a></li>  
                                      <li><a class="dropdown-item" href="servletLogout"><i class="bi bi-box-arrow-right"></i> Cerrar sesión</a></li>

                                </ul>
                            </div>
                          </div>
                        </div>
                      </div>
                  </div>
              </div>
            </div>
        </header>
        <article>
          <section>
            <div class ="container-fluid ">
                 
                <div class="row row-cols-auto justify-content-center">
                    <%
                    
                    
                    List<ListaDTO> listas = (List)request.getAttribute("listas");
                    
                    for (ListaDTO l : listas) {
                           
                    %> 
                  <div class="col py-4">
                    
                    <div class="card shadow" style="width: 18rem;">
                      <div class="card-header">
                          Titulo: <%=l.getNombre()%>
                      </div>
                      <ul class="list-group list-group-flush">
                        <li class="list-group-item">Descripcion: <%=l.getDescripcion() %></li>
                        <li class="list-group-item">Atributos: <%= l.getAtributos()%></li>
                      </ul> 
                      <div class="card-body">
                        <div class="row row-cols-auto align-items-center justify-content-center">
                            <div class="btn-group" role="group" aria-label="Basic example">
                                <a class="btn btn-primary" href="servletFiltrarCompradores?idLista=<%= l.getIdLISTA()%>&accion=editar" role="button">Editar</a>
                                <a class="btn btn-primary" href="servletFiltrarCompradores?idLista=<%= l.getIdLISTA()%>" role="button">Listar</a>
                                <a class="btn btn-primary" href="servletEnviarMensaje?idLista=<%= l.getIdLISTA()%>" role="button">Notificar</a>
                                <a class="btn btn-primary" href="servletBorrarLista?idLista=<%= l.getIdLISTA()%>" role="button">Borrar</a>
                            </div>
                        </div> 
                      </div>
                    </div>
                       <%
                          }
                    %> 
                </div>
          </section>
    </article>
                
    <footer class="py-3 my-4">
        <ul class="nav justify-content-center border-bottom pb-3 mb-3">
          <li class="nav-item"><a href="#" class="nav-link px-2 text-muted">Home</a></li>
          <li class="nav-item"><a href="#" class="nav-link px-2 text-muted">Contacto</a></li>
          <li class="nav-item"><a href="#" class="nav-link px-2 text-muted">Ayuda</a></li>
          <li class="nav-item"><a href="#" class="nav-link px-2 text-muted">FAQs</a></li>
          <li class="nav-item"><a href="#" class="nav-link px-2 text-muted">Sobre nosotros</a></li>
        </ul>
        <p class="text-center text-muted">© 2022 Company, Inc</p>
    </footer>      
      
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

    </body>
</html>
