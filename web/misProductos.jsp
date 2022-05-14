<%-- 
    Document   : misProductos
    Created on : 15-abr-2022, 12:38:41
    Author     : Miguel Angel Cosano Ramirez
--%>


<%@page import="java.text.SimpleDateFormat"%>
<%@page import="dto.UsuarioDTO"%>
<%@page import="dto.SubastaDTO"%>
<%@page import="java.util.List"%>

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
  <% UsuarioDTO user = (UsuarioDTO)session.getAttribute("usuario"); %>
  
  <body>

      <header>
        <div class="px-3 py-3 bg-dark text-white shadow">
          <div class="container">
              <div class="row justify-content-between">
                  <div class ="col-auto">
                      <a href="servletListadoSubastas" class="text-light ">
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
                    <i class="bi bi-person-circle"></i> Mi perfil
                  </button>           
                  <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1" style="width: 300px">

                      <label class="px-3 pt-2">
                          <i class="bi bi-coin"></i>
                          Saldo disponible: <%=user.getSaldo() %> EUR
                      </label> 
                    
                      <hr>
                    <li><a class="dropdown-item" href="servletMisCompras"><i class="bi bi-basket2"></i> Mis compras</a></li>
                    <li><a class="dropdown-item" href="servletListadoMisProductos"><i class="bi bi-bag-check"></i> Mis ventas</a></li>
                    <li><a class="dropdown-item" href="servletAccesoModificarProducto"><i class="bi bi-plus-circle"></i> Publicar producto</a></li>   
                    <li><a class="dropdown-item" href="servletListarNotificaciones"><i class="bi bi-bell"></i> Notificaciones</a></li>
                    <li><a class="dropdown-item" href="servletLogout"><i class="bi bi-box-arrow-right"></i> Cerrar sesion</a></li>

                  </ul>
                </div>
                          </div>
                          </div>
                              
                      </div>
                  </div>
              </div>

          </div>
        
        <div class="px-3 py-2 mb-3 shadow">
          
          <div class="container">
            <form method="post" action="servletFiltrarMisCompras">
            <div class="row align-items-center">
                <div class="col">
                    <input class="form-control" type="text" placeholder="Nombre del producto"  name="nombreSubasta"> 
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
                  <input class="form-control" type="hidden" value=<%=user.getIdUsuario() %>  name="usuario" onChange="this.form.submit()"> 
                <button type="submit" value="Filtrar" class="btn btn-primary">Filtrar</button>
                
              </div>
            </div>
            </form>
 
          
        </div>
      </header>
      
      <article>
          <section>
            
                <div class="px-3 py-2 mb-3">
                    <%
                        List<SubastaDTO> subastas = (List)request.getAttribute("subastas");
                        if(subastas.isEmpty()){
                            
                        
                        %>
                        <div class="container py-2 align-items-center justify-content-center" >
                            <h2 class="text-center">No tienes productos en venta</h2>
                           
                        </div>
                        
                        <%
                            }else{
                        
                        for(SubastaDTO sub : subastas){
                            if(sub.getVendedor().getIdUsuario()==user.getIdUsuario()){
                                
                            
                        %>
                    <div class="container py-2">
                      <div class="card shadow" style"max-height: 300px; min-height:200px">
                        <h5 class="card-header">Categoria: <%= sub.getProducto().getCategoria() %></h5>
                        <div class="card-body">
                          <div class="row row-cols-auto align-items-center" >
                            <div class="col ps-4">
                              <img src=<%=sub.getProducto().getUrlFoto() %> class="card-img-top" alt=<%=sub.getProducto().getTitulo() %> style="width: 10rem; object-fit: contain; max-height: 100px; min-height:100px">
                            </div>
                            
                            <div class="col ps-5">
                              <div class="row">
                                  Nombre del producto: <%=sub.getProducto().getTitulo() %>
                              </div>
                              
                              <%
                             if(!sub.getProducto().getEstado().equals("En venta")){
                             SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                             
                             %>
                              <div class="row">
                                Fecha finalización subasta: <%=format.format(sub.getCierre())%>
                              </div>
                              <%}%>
                              <div class="row">
                              Coste:  <%=sub.getPujaMaxima() %> EUR
                              </div>
                              <div class="row">
                               Vendedor: <%=sub.getVendedor().getNombre()%>
                               </div>
                               
                            </div>
                            <div class="col ps-5">
                              <div class="d-flex" style="height: 120px;">
                                <div class="vr"></div>
                              </div>
                            </div>
                            <div class="col-5 ps-4">
                              <%=sub.getProducto().getDescripcion() %>
                            </div>
                            <div class="col">
                            
                        </div>
                            
                            
                          </div>
                            <div class="btn-group" role="group" aria-label="Basic example">
                                <a class="btn btn-primary" href="servletAccesoModificarProducto?subasta=<%= sub.getIdSUBASTA()%>&id=<%=user.getIdUsuario()%>" role="button">Modificar</a>
                                <a class="btn btn-primary" href="servletBorrarSubasta?subasta=<%= sub.getIdSUBASTA() %>" role="button">Borrar</a>
                                <% if(sub.getProducto().getEstado().equals("En venta")){%>
                                <a class="btn btn-primary" href="servletTerminarSubasta?subasta=<%= sub.getIdSUBASTA() %>&id=<%=user.getIdUsuario()%>" role="button">TerminarSubasta</a>
                                <%}%>
                            </div>
                        </div>
                            
                      </div>
                    </div>
                        <%
                            
                              }
                            }
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
