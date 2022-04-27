

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
  <% Usuario user = (Usuario)session.getAttribute("usuario");
     Double saldo = (Double)session.getAttribute("saldo");
     if(saldo != null){
         user.setSaldo(saldo);
     }
  %>
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
            <form method="post" action="servletFiltrarSubastas">
            <div class="row align-items-center">
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
                <div class="form-check">
                    <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault" name="filtroFavorito">
                     <label class="form-check-label" for="flexCheckDefault">Solo favoritos </label>
                </div>
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
                    if(subastas.isEmpty()){

                        %>
                        <div class="container py-2 align-items-center justify-content-center" >
                            <h2 class="text-center">No existen subastas</h2>
                        </div>
                        <%
                            }else{
                        
                    for (Subasta sub :subastas) {
                         if(sub.getComprador() == null){
                    %> 
                    <!-- Modal -->
                    <div class="modal fade" id="modal<%=sub.getIdSUBASTA()%>" tabindex = "-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                      <div class="modal-dialog">
                        <div class="modal-content">
                          <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Ventana de Puja</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                          </div>
                            <form method="post" action="servletPujar?">
                                <div class="modal-body">
                                    <div class="row row-cols-auto align-items-center justify-content-center">
                                        <div class="col-auto"> Cantidad:</div>
                                        <div class="col-auto">
                                            <%
                                        if(sub.getPujaMaxima()!= null){
                                            %>
                                            <input class="form-control" type="number" min=<%=sub.getPujaMaxima() +1 %>  name="puja" style="max-width: 250px" placeholder="Puja actual: <%=sub.getPujaMaxima() %>" required>    
                                            <%
                                        }else{
                                            %>
                                        <input class="form-control" type="number" min=<%=sub.getPrecioInicial()+ 1%>  name="puja" style="max-width: 250px" placeholder="Puja inicial <%=sub.getPrecioInicial() %>" required>  
                                            <%
                                        }
                                            %>
                                        </div>
                                        
                                    </div>
                                    
                                </div>
                          <div class="modal-footer">
                              <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                                <button type="submit" class="btn btn-primary" value=<%=sub.getPujaMaxima() %>>Pujar</button>
                                
                                <input class="form-control" type="hidden" value=<%=sub.getIdSUBASTA() %>  name="subasta" >
                                <input class="form-control" type="hidden" value=<%=user.getIdUSUARIO() %>  name="usuario" >  
                          </div>
                            </form> 
                        </div>
                      </div>
                    </div>
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
                        <li class="list-group-item">Precio inicial: <%= sub.getPrecioInicial() %> EUR </li>
                        <li class="list-group-item">Puja actual: <%= sub.getPujaMaxima() %> EUR</li>
                        <li class="list-group-item">Vendedor: <%= sub.getVendedor().getNombre() %> </li>
                      </ul>
                      <div class="card-body">
                        <div class="row row-cols-auto align-items-center justify-content-center">
                          <div class="col">
                              <%
                                  if(user.getSaldo() > sub.getPujaMaxima()){
                                      
                                  
                                  %>
                                  <button type="button" class="btn btn-primary" value=<%=sub.getPujaMaxima() %> data-bs-toggle="modal" data-bs-target=#modal<%=sub.getIdSUBASTA()%>>
                              Pujar
                            </button> 
                                  <%
                                      }else{


                                      %>
                                      <span class="d-inline-block" tabindex="0" data-toggle="tooltip" title="Saldo insuficiente">
                                        <button class="btn btn-secondary" style="pointer-events: none;" type="button" disabled>Pujar</button>
                                      </span>
                                      <%
                                          }
                                          %>
                          </div>
                          <div class="col-auto">
                                  <form method="post" action="servletFavorito" id="fav" autocomplete="off">
                                     <div class="form-check form-switch">
                                        <label class="form-check-label" >
                                            Favorito
                                        </label>

                                          <input class="form-control" type="hidden" value=<%=sub.getIdSUBASTA() %>  name="subasta" onChange="this.form.submit()" >
                                          <input class="form-control" type="hidden" value=<%=user.getIdUSUARIO() %>  name="usuario" onChange="this.form.submit()"> 
                                          
                                           <input class="form-check-input" type="checkbox" role="switch" id="flexSwitchCheckDefault" name="favorito" onChange="this.form.submit()">
                                           
                                          <%
                                            List<Producto> productos = (List)request.getAttribute("productos");
                                            if(productos == null){
                                                productos = user.getProductoList();
                                            }
                                            for (Producto pro: productos){
                                            if(pro.getIdPRODUCTO() == sub.getProducto().getIdPRODUCTO() ){
                                            %>
                                            
                                            <input class="form-check-input" type="checkbox" role="switch" id="flexSwitchCheckChecked" name="favorito" onChange="this.form.submit()" checked="true">
                                            <%
                                            }
                                            %>
                                           
                                           <%
                                            
                                            }
                                            
                                            %>
                                        </div>
                                  </form>
                            
                          </div>
                          </div> 
                          </form>
                              
                              
                          
                         
                     
                      </div>
                

                    </div>
                     
                  </div>
                       <%
                          }
                        }
                        }
                    %> 
                </div>
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
