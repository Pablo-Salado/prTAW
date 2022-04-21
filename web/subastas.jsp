

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
    <title>JSP Page</title>
  </head>
  <% Usuario user = (Usuario)session.getAttribute("usuario"); %>
  <body>

      <header>
        <div class="px-3 py-0 bg-dark text-white shadow">
          <div class="container">
            <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-md-between py-3">
              <a href="#" class="d-flex align-items-center col-md-3 mb-2 mb-md-0 text-dark text-decoration-none">
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
                    <li><a class="dropdown-item" href="#">Mis ventas</a></li>
                    <li><a class="dropdown-item" href="#">Mis compras</a></li>
                    <li><a class="dropdown-item" href="#">Productos favoritos</a></li>
                    <li><a class="dropdown-item" href="servletLogout">Cerrar sesion</a></li>
                  </ul>
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
                        <p class="card-text"><%= sub.getProducto().getDescripcion() %></p>
                      </div>
                      <ul class="list-group list-group-flush">
                        <li class="list-group-item">Precio inicial: EUR <%= sub.getPrecioInicial() %></li>
                        <li class="list-group-item">Puja actual: EUR <%= sub.getPujaMaxima() %></li>
                        <li class="list-group-item">Fecha limite: <%= sub.getCierre() %> </li>
                        <li class="list-group-item">Vendedor: <%= sub.getVendedor().getNombre() %> </li>
                      </ul>
                      <div class="card-body">
                          <form method="post" action="servletPujar?">
                        <div class="row row-cols-auto align-items-center justify-content-center">
                          <div class="col">
                                  <button type="submit" class="btn btn-primary" value=<%=sub.getPujaMaxima() %>>
                              Pujar
                            </button>    
                              <input class="form-control" type="hidden" value=<%=sub.getIdSUBASTA() %>  name="subasta" >
                              <input class="form-control" type="hidden" value=<%=user.getIdUSUARIO() %>  name="usuario" >  
                          </div>
                              <div class="col">
                                  <%
                                      if(sub.getPujaMaxima()!= null){
                                      %>
                              <input class="form-control" type="number" min=<%=sub.getPujaMaxima()%>  name="puja" style="max-width: 150px">    
                              <%
                                  }else{
                                  %>
                                  <input class="form-control" type="number" min=<%=sub.getPrecioInicial() %>  name="puja" style="max-width: 150px">  
                                  <%
                                  }
                                  %>
                          </div>
                          </div> 
                          </form>
                          <div class="row row-cols-auto align-items-center py-1 justify-content-center">
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
                          
                         
                     
                      </div>
                

                    </div>
                     
                  </div>
                       <%
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