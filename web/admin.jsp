<%-- 
    Author     : X430F
--%>

<%@page import="java.util.List"%>
<%@page import="entity.Subasta"%>
<%@page import="entity.Usuario"%>
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
        <title>Admin</title>
    </head>
    <% 
        Usuario user = (Usuario)session.getAttribute("usuario");
    %>
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
                                  <i class="bi bi-person-circle"></i> Admin
                                </button>           
                                <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1" style="width: 300px">
                                      <li><a class="dropdown-item" href="#">Listar productos</a></li>
                                      <li><a class="dropdown-item" href="servletAdminListarUsuarios">Listar usuarios</a></li>
                                      <li><a class="dropdown-item" href="">Dar de alta a usuarios de marketing</a></li>
                                      <li><a class="dropdown-item" href="servletLogout"><i class="bi bi-box-arrow-right"></i> Cerrar sesión</a></li>

                                </ul>
                            </div>
                          </div>
                          <div class ="col-auto">
                              <a href ="servletAdminAccesoModificarProducto" class="btn btn-primary">Crear producto</a>
                          </div>
                        </div>
                      </div>
                  </div>
              </div>

          </div>
            <div class="px-3 py-2 mb-3 shadow">
          
                <div class="container">
                  <form method="post" action="servletAdminFiltrarSubastas">
                  <div class="row align-items-center">
                    <div class="col">
                        <input class="form-control" type="text" placeholder="Nombre de la subasta"  name="nombreSubasta"> 
                    </div>
                    <div class="col">
                      <select class="form-select" aria-label="Default select example" style="width: auto;" name="categoria">
                        <option selected>CATEGORIAS</option>
                        <option value="MOTOR">MOTOR</option>
                        <option value="DEPORTE">DEPORTE</option>
                        <option value="HOGAR">HOGAR</option>
                        <option value="INFORMATICA">INFORMATICA</option>
                        <option value="IMAGEN Y SONIDO">IMAGEN Y SONIDO</option>
                        <option value="TELEFONIA">TELEFONIA</option>
                        <option value="MODA">MODA</option>
                        <option value="JUEGOS">JUEGOS</option>
                        <option value="AFICIONES Y OCIO">AFICIONES Y OCIO</option>
                        <option value="OTROS">OTROS</option>
                      </select>
                    </div>
                    <div class="col-auto">
                      <label class="col-form-label">Rango de precio:</label>
                    </div>
                    <div class="col ">
                      <input class="form-control" type="number" min="0" placeholder="Precio minimo"  name="minPrice"> 

                    </div>
                    <div class="col-auto">
                      <label class="col-form-label">-</label>
                    </div>
                    <div class="col">
                      <input class="form-control" type="number" min="0" placeholder="Precio maximo" name="maxPrice">

                    </div>
                    <div class="col">

                    </div>
                    <div class="col-auto">
                        <input class="form-control" type="hidden" value=<%=user.getIdUSUARIO() %>  name="usuario" onChange="this.form.submit()"> 
                      <button type="submit" value="Filtrar" class="btn btn-primary">Filtrar</button>

                    </div>
                  </div>
                  </form>


            </div>
        </header>
        <article>
          <section>
            <div class ="container-fluid ">
                 
                <div class="row row-cols-auto justify-content-center">
                    <%
                    
                    
                    List<Subasta> subastas = (List)request.getAttribute("subastas");
                    
                    for (Subasta sub :subastas) {
                         
                    %> 
                  <div class="col py-4">
                    
                    <div class="card shadow" style="width: 18rem;">
                      <div class="card-header">
                          Categoría: <%=sub.getProducto().getCategoria() %>
                      </div>
                        <img src=<%= sub.getProducto().getUrlFoto() %> class="card-img-top" alt=<%= sub.getProducto().getTitulo() %> style="max-height: 150px; object-fit: contain;">
                             <hr>  
                      <div class="card-body">
                        <h5 class="card-title"><%= sub.getProducto().getTitulo() %></h5>
                        <p class="card-text overflow-auto" style="min-height:  70px"><%= sub.getProducto().getDescripcion() %></p>
                      </div>
                      <ul class="list-group list-group-flush">
                        <li class="list-group-item">Precio inicial: EUR <%= sub.getPrecioInicial() %></li>
                        <li class="list-group-item">Puja actual: EUR <%= sub.getPujaMaxima() %></li>
                        <li class="list-group-item">Fecha limite: <%= sub.getCierre() %> </li>
                        <li class="list-group-item">Vendedor: <%= sub.getVendedor().getNombre() %> </li>
                        <% if(sub.getComprador() != null){ %>
                            <li class="list-group-item">Comprador: <%= sub.getComprador().getNombre() %> </li>
                        <% } %>
                      </ul>
                      <div class="card-body">
                        <div class="row row-cols-auto align-items-center justify-content-center">
                            <div class="btn-group" role="group" aria-label="Basic example">
                                <a class="btn btn-primary" href="servletAdminAccesoModificarProducto?subasta=<%= sub.getIdSUBASTA() %>&id=<%=user.getIdUSUARIO()%>" role="button">Modificar subasta</a>
                                <a class="btn btn-primary" href="servletAdminBorrarSubasta?subasta=<%= sub.getIdSUBASTA() %>" role="button">Borrar subasta</a>
                            </div>
                        </div> 
                      </div>
                    </div>
                  </div>
                       <%
                          }
                    %> 
                </div>
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
