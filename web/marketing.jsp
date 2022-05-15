<%-- 
    Document   : marketing
    Created on : 04-may-2022, 13:36:01
    Author     : Javier Santiburcio
--%>
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
        <title>Marketing</title>
    </head>
    <% Usuario user = (Usuario)session.getAttribute("usuario");%>
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
                                      <li><a class="dropdown-item" href="servletListarListas?usuario=<%=user.getIdUSUARIO()%>">Mis Listas</a></li>
                                      <li><a class="dropdown-item" href="servletMarketing?usuario=<%=user.getIdUSUARIO()%>">Compradores</a></li>                                      
                                      <li><a class="dropdown-item" href="servletLogout"><i class="bi bi-box-arrow-right"></i> Cerrar sesión</a></li>
                                </ul>
                            </div>
                          </div>
                        </div>
                      </div>
                  </div>
              </div>

          </div>
            <%
                    String accion = (String) request.getAttribute("accion");
                    
                    String nombre = "";
                    String apellidos = "";
                    String sexo = "";
                    String email = "";
                    String domicilio = "";
                    String ciudad_residencia = "";
                    String minEdad = "";
                    String maxEdad = "";
                    String tipo_usuario = "";
                    String saldo = "";
                    String idLista = "";
                    
                    if(accion.compareTo("todos") != 0){
                        nombre = (String) request.getAttribute("nombre");
                        apellidos = (String) request.getAttribute("apellidos");
                        sexo = (String) request.getAttribute("sexo");
                        email = (String) request.getAttribute("email");
                        domicilio = (String) request.getAttribute("domicilio");
                        ciudad_residencia = (String) request.getAttribute("ciudad_residencia");
                        minEdad = (String) request.getAttribute("minEdad");
                        maxEdad = (String) request.getAttribute("maxEdad");
                        tipo_usuario = (String) request.getAttribute("tipo_usuario");
                        saldo = (String) request.getAttribute("saldo");
                        idLista = (String) request.getAttribute("idLista");
                    }
                    
                    List<Usuario> usuarios = (List)request.getAttribute("usuarios");
                   
                    
            %>
            <div class="px-3 py-2 mb-3 shadow">
          
                <div class="container">
                  <form method="post" action="servletFiltrarCompradores">
                  <div class="row align-items-center">
                    <!--<div class="col">
                        <input class="form-control" type="text" placeholder="idUsuario"  name="idUsuario"> 
                    </div>-->
                    <div class="col">
                        <input class="form-control" type="text" placeholder="Nombre" style="width: auto;" name="nombre" value="<%=nombre%>"> 
                    </div>
                    <div class="col">
                        <input class="form-control" type="text" placeholder="Apellidos" style="width: auto;" name="apellidos" value="<%=apellidos%>"> 
                    </div>
                    <div class="col">
                      <select class="form-select" aria-label="Default select example" style="width: auto;" name="sexo" >
                        <%
                            if(sexo.compareTo("H") == 0){
                        %>
                        <option selected>Mujer</option>
                        <option value="M">Hombre</option>
                        <option value="Sexo">Sexo</option>
                        <%
                            }else if(sexo.compareTo("M") == 0){
                        %>
                        <option selected>Hombre</option>
                        <option value="H">Mujer</option>
                        <option value="Sexo">Sexo</option>
                        <%
                            }else{
                        %>
                        <option selected>Sexo</option>
                        <option value="M">Hombre</option>
                        <option value="H">Mujer</option>                        
                        <%                                                 
                           }
                        %>
                      </select>
                    </div>
                    <div class="col">
                        <input class="form-control" type="text" placeholder="Email" style="width: auto;" name="email" value="<%=email%>"> 
                    </div>
                    <div class="col">
                        <input class="form-control" type="text" placeholder="Domicilio" style="width: auto;"  name="domicilio" value="<%=domicilio%>"> 
                    </div>
                    <div class="col">
                        <input class="form-control" type="text" placeholder="Ciudad de residencia" style="width: auto;" name="ciudad_residencia" value="<%=ciudad_residencia%>"> 
                    </div>
                    <div class="col-auto">
                      <label class="col-form-label">Edad:</label>
                    </div>
                    <div class="col ">
                      <input class="form-control" type="number" min="0" placeholder="Mínima"  name="minEdad" value="<%=minEdad%>"> 
                      
                    </div>
                    <div class="col-auto">
                      <label class="col-form-label">-</label>
                    </div>
                    <div class="col">
                      <input class="form-control" type="number" min="0" placeholder="Máxima" name="maxEdad" value="<%=maxEdad%>">
                      
                    </div>
                    <div class="col">
                      <select class="form-select" aria-label="Default select example" style="width: auto;" name="tipo_usuario">
                        <%
                            if(tipo_usuario.compareTo("CV") == 0){
                        %>
                        <option selected>CV</option>
                        <option value="ADMINISTRADOR">ADMINISTRADOR</option>
                        <option value="Tipo">Tipo</option>
                        <%
                            }else if(tipo_usuario.compareTo("ADMINISTRADOR") == 0){
                        %>
                        <option selected>ADMINISTRADOR</option>
                        <option value="CV">CV</option>
                        <option value="Tipo">Tipo</option>
                        <%
                            }else{
                        %>
                        <option selected>Tipo</option>
                        <option value="ADMINISTRADOR">ADMINISTRADOR</option>
                        <option value="CV">CV</option>                       
                        <%                                                 
                           }
                        %>
                      </select>
                    </div>
                    
                    <div class="col">
                        <input class="form-control" type="text" placeholder="Saldo"  name="saldo" value="<%=saldo%>"> 
                    </div>  
                    
                    
                    <div class="col">
                        
                    </div>
                    <div class="col-auto">
                      <input class="form-control" type="hidden" value=<%=user.getIdUSUARIO() %>  name="usuario" onChange="this.form.submit()"> 
                      <button type="submit" name="accion" value="filtrar" class="btn btn-primary">Filtrar</button>
                      <%
                          //if(accion.compareTo("milista") != 0){
                      %>
                      <button type="submit" name="accion" value="guardar" class="btn btn-primary">Guardar</button>
                      <%
                          //}else{                         
                      %>
                      <!--<button type="submit" name="accion" value="guardar" class="btn btn-primary">Guardar como nueva lista</button>
                      <button type="submit" name="accion" value="editar" class="btn btn-primary">Guardar lista editada</button>
                      -->
                      <%
                          //}
                      %>
                    </div>
                  </div>
                  </form>

                </div>
            </div>
        </header>
        <article>
          <section>
            <div class ="container-fluid ">
                 
                <div class="row row-cols-auto justify-content-center">
                    <%
                    
                    
                    for (Usuario u :usuarios) {
             
                    %> 
                  <div class="col py-4">
                    
                    <div class="card shadow" style="width: 18rem;">
                      <div class="card-header">
                          idUSUARIO: <%=u.getIdUSUARIO()%>
                      </div>
                      <ul class="list-group list-group-flush">
                        <li class="list-group-item">Nombre: <%=u.getNombre() %></li>
                        <li class="list-group-item">Apellidos: <%= u.getEpellidos()%></li>
                        <li class="list-group-item">Sexo: <%= u.getSexo() %> </li>
                        <li class="list-group-item">Email: <%=  u.getEmail()%> </li>
                        <li class="list-group-item">Domicilio: <%= u.getDomicilio()%></li>
                        <li class="list-group-item">Ciudad de residencia: <%=u.getCiudadResidencia() %></li>
                        <li class="list-group-item">Edad: <%= u.getEdad()%></li>
                        <li class="list-group-item">Tipo de usuario: <%= u.getTipoUsuario()%></li>
                        <li class="list-group-item">Saldo: <%= u.getSaldo()%></li>
                      </ul> 
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
