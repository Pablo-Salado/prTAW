<%-- 
    Document   : misProductos
    Created on : 15-abr-2022, 12:38:41
    Author     : Usuario
--%>

<%@page import="entity.Usuario"%>
<%@page import="java.util.List"%>
<%@page import="entity.Subasta"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">
        <title>JSP Page</title>
    </head>
    <%Usuario user = (Usuario)session.getAttribute("usuario");%>
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
                    <li><a class="dropdown-item" href="#">Mis compras</a></li>
                    <li><a class="dropdown-item" href="servletListadoMisProductos">Mis ventas</a></li>
                    <li><a class="dropdown-item" href="servletAccesoModificarProducto">Publicar producto</a></li>
                    <li><a class="dropdown-item" href="servletLogout">Cerrar sesion</a></li>
                  </ul>
                    
                </div>
              </div>
            </div>
          </div>
        </div>
        
        <div class="px-3 py-2 mb-3 shadow">
         
      </header>
       

       
        <article>
            <section>
 <%
                List<Subasta> subastas = (List)request.getAttribute("subastas");
                if(subastas.isEmpty()){
                    %>
                    <h1>El usuario no tiene productos en subasta</h1>
                <%}
            for (Subasta sub :subastas) {
                if(sub.getVendedor().getIdUSUARIO()==user.getIdUSUARIO()){
               
        %>   
        
        <div class="px-3 py-2 mb-3">
            <div class="container">
                <div class="row px-3 py-4 mb-3 shadow border rounded" id="productoComprado">
                    <div class="col-md-2  ">
                        <img src=<%= sub.getProducto().getUrlFoto()%> class="card-img shadow border rounded" alt=<%= sub.getProducto().getTitulo()%> >
                    </div>
                    
                    <div class="col col-lg-auto " >

                        <div class="col px-3">
                            <div class="col">
                                <p class="text-primary">
                                    Nombre del producto: <small class="text-dark"><%= sub.getProducto().getTitulo()%></small>
                                </p> 
                            </div>
                            <div class="col">
                                    <p class="text-primary">
                                    Fecha de apertura: <small class="text-dark"> <%= sub.getApertura()%></small>
                                    </p> 
                            </div>

                            
                            <div class="row">
                                <p class="text-primary">
                                 Precio inicial: <small class="text-dark">  <%= sub.getPrecioInicial()%></small>
                                 </p> 

                            </div>
                            <div class="row">
                                <p class="text-primary">
                                Precio actual: <small class="text-dark">  <%= sub.getPujaMaxima()%></small>
                                </p> 
                            </div>
                            


                            <div class="row">
                                <p class="text-primary">
                                Vendedor:<small class="text-dark"> <%= sub.getVendedor().getNombre()%></small>
                                </p>
                            </div>
                                <div class="row">
                                <p class="text-primary">
                                Estado:<small class="text-dark"> <%= sub.getProducto().getEstado()%></small>
                                </p>
                            </div>
                        </div >

                    </div>
                            
                    <div class="col align-items-center shadow border rounded">
                        
                        <div class="row" title="" id="texto">
                            <p class="text-primary">Descripcion</p>
                            -<%= sub.getProducto().getDescripcion()%>  
                        </div>


                    </div>
                    <div class="row  mt-3 ">
                        <div class="col">
                            <div class="btn-group" role="group" aria-label="Basic example">
                                <a class="btn btn-primary" href="servletAccesoModificarProducto?subasta=<%= sub.getIdSUBASTA()%>&id=<%=user.getIdUSUARIO()%>" role="button">Modificar</a>
                                <a class="btn btn-primary" href="servletBorrarSubasta?subasta=<%= sub.getIdSUBASTA() %>" role="button">Borrar</a>
                                <a class="btn btn-primary" href="servletTerminarSubasta?subasta=<%= sub.getIdSUBASTA() %>&id=<%=user.getIdUSUARIO()%>" role="button">TerminarSubasta</a>
                            </div>
                        </div>
                    </div>

                </div>


            </div>
        </div>



            </section>
        </article>
        
                           
                    
        </tr>
        <%
            }
            }
        %>
           <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

    </body>
</html>
