<%-- 
    Document   : notificaciones
    Created on : 25-abr-2022, 15:09:21
    Author     : Gorpax
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="entity.Notificaciones"%>
<%@page import="entity.Producto"%>
<%@page import="entity.Usuario"%>
<%@page import="java.util.List"%>
<%@page import="entity.Subasta"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="style.css" rel="stylesheet">
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">
    <title>Página principal</title>
  </head>
  <% Usuario user = (Usuario)session.getAttribute("usuario"); %>
  
  <body>

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
      
      <article>
          <section>
            
                <div class="px-3 py-2 mb-3">
                    <%
                        List<Notificaciones> notificaciones = (List)request.getAttribute("notificaciones");
                        List<Notificaciones> not = new ArrayList<Notificaciones>();
                        for(Notificaciones noti : notificaciones){
                            if(noti.getIdUsuario().getIdUSUARIO() == user.getIdUSUARIO()  && !not.contains(noti)){
                                not.add(noti);
                            }
                        }
                        if(not.isEmpty()){
                            
                        
                        %>
                        <div class="container py-2 align-items-center justify-content-center" >
                            <h2 class="text-center">No tiene ninguna notificacion</h2>
                        </div>
                        <%
                            }else{
                        int cont = 1;
                        for(Notificaciones n : not){
                                
                            if(n.getIdUsuario().getIdUSUARIO() == user.getIdUSUARIO()){
                        %>
                     <div class="row justify-content-center">
            <div class="container py-2 col-6">
                <div class="card shadow">
                    <h5 class="card-header">Notificacion <%=cont%> </h5>
                    <div class="card-body">
                        <div class="row align-items-center">

                            <div class="col">
                            <div class="d-flex" style="height: 120px;">
                                <div class="vr"></div>
                            </div>
                            </div>

                            <div class="col-6">
                            Le notificamos que la subasta con titulo: <%=n.getIdSubasta().getProducto().getTitulo() %>, con id: <%= n.getIdSubasta().getIdSUBASTA() %> ha terminado, le informamos
                            que usted <%=n.getGanador() %> de la puja.
                            </div>

                            <div class="col">
                                <div class="d-flex" style="height: 120px;">
                                    <div class="vr"></div>
                                </div>
                            </div>

                            <div class="col-5 align-self-end">
                                <div class="row justify-content-end px-2">
                                    <form method="post" action="servletEliminarNotificacion">
                                        <button type="submit" class="btn btn-danger" style="width: 110px;">
                                        <i class="bi bi-backspace-reverse-fill"> Eliminar</i>
                                    </button>
                                        <input class="form-control" type="hidden" value=<%=n.getId() %>  name="notificacion" >
                                    </form>
                                    
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
                        <%
                            
                              }
                              cont++;
                            }

                        }   
                            %>
                </div>
          
                    
                
          </section>
      </article>
      <nav aria-label="Page navigation example">
        <ul class="pagination justify-content-center">
          <li class="page-item disabled">
            <a class="page-link">Previous</a>
          </li>
          <li class="page-item"><a class="page-link" href="#">1</a></li>
          <li class="page-item"><a class="page-link" href="#">2</a></li>
          <li class="page-item"><a class="page-link" href="#">3</a></li>
          <li class="page-item">
            <a class="page-link" href="#">Next</a>
          </li>
        </ul>
      </nav>
      
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