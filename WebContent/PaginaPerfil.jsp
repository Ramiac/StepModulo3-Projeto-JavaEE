<%@page import="model.entity.Usuario"%>
<%@page import="model.entity.Pessoa"%>
<%@page import="model.entity.Publicacao"%>
<%@page import="model.entity.Comentario"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>InstaFood - Pagina do Perfil</title>
<script src="js/valida.js"></script>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj" crossorigin="anonymous"></script>
<link rel="stylesheet" href="css/geral.css">
<link rel="stylesheet" href="css/feed-cards.css">
<link href="css/header.css" rel="stylesheet">
</head>
<body>
<header class="p-3 mb-3 border-bottom">
    <div class="container">
      <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
        <a href="PaginaPrincipal.jsp" class="d-flex align-items-center mb-2 mb-lg-0 text-dark text-decoration-none">
            <img src="img/Logo_Instafood.png" alt="" width="40" height="40">
        </a>

        <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
          <li><a href="SvFeedPublicacoes" class="nav-link px-2 link-dark">Feed de Publicações</a></li>
          <li><a href="SvNovaPublicacao" class="nav-link px-2 link-dark">Nova Publicação</a></li>
          <li><a href="BuscaRestaurante.jsp" class="nav-link px-2 link-dark">Busca Restaurante</a></li>
          <li><a href="BuscaHotel.jsp" class="nav-link px-2 link-dark">Busca Hotel</a></li>
        </ul>

        <div class="dropdown text-end">
          <a href="#" class="d-block link-dark text-decoration-none dropdown-toggle" id="dropdownUser1" data-bs-toggle="dropdown" aria-expanded="false">
            <img src="img/perfil.png" alt="mdo" width="32" height="32" class="rounded-circle">
          </a>
          <ul class="dropdown-menu text-small" aria-labelledby="dropdownUser1">
            <li><a class="dropdown-item" href="SvFeedPessoal">Feed Pessoal</a></li>
            <li><a class="dropdown-item" href="SvCadastraCompletaPerfil">Completa / Editar seu perfil</a></li>
            <li><a class="dropdown-item" href="SvBuscaEstabUsuario">Editar um dos seus estabelecimentos</a></li>
            <li><a class="dropdown-item" href="CadastroHotel.jsp">Cadastrar um hotel</a></li>
            <li><a class="dropdown-item" href="CadastroRestaurante.jsp">Cadastrar um restaurante</a></li>
            <li><hr class="dropdown-divider"></li>
            <li><a class="dropdown-item" href="SvSair">Sair</a></li>
          </ul>
        </div>
      </div>
    </div>
  </header>
    <main>
    <div class="container mt-5 mb-5">
        <h3>Feed Pessoal</h3>
        <% 
            List<Publicacao> listaPublicacoes = (List<Publicacao>) request.getSession().getAttribute("listaPublicacoes");
            if (listaPublicacoes != null){
                for (Publicacao p: listaPublicacoes){
                String nomeUsuarioEstab = null;
                String tipoPublicacao = null;
                long id;
                
                if (p.getRestaurante() != null){
                    nomeUsuarioEstab = p.getRestaurante().getNome(); // Trocar isso por Link para pagina info
                    tipoPublicacao = "Publicação profissional: Restaurante";
                    id = p.getRestaurante().getId();
                }else if (p.getHotel() != null){
                    nomeUsuarioEstab = p.getHotel().getNome(); // Troca isso por Link para pagina info
                    tipoPublicacao = "Publicação profissional: Hotel";
                    id = p.getHotel().getId();
                }else {
                    Pessoa pess = new Pessoa();
                    id = p.getUsuario().getId();
                    pess.setId(id);
                    pess = pess.localizaPeloId();
                    nomeUsuarioEstab = pess.getNome();
                    tipoPublicacao = "Publicação pessoal";
                }

        %>
        <div class="row d-flex align-items-center justify-content-center mb-2">
          <div class="col-md-6">
            <div class="card">
              <div class=" justify-content-between p-2 px-3">
                <div class=" flex-row align-items-center"> <img src="img/perfil.png" width="50" class="rounded-circle">
                  <div class=" flex-column ml-2">
                    <a href="SvDispatchPerfisInfo?id=<%=id%>&tipoPublicacao=<%= tipoPublicacao %>"><span class="font-weight-bold"><%= nomeUsuarioEstab %></span></a>
                    <br>
                    <small class="text-primary"><%= tipoPublicacao %></small>
                  </div>
                </div>
                <div class=" flex-row mt-1 ellipsis">
                  <small class="mr-2">Data: <%=p.getData()%> &nbsp;&nbsp;&nbsp; Hora: <%=p.getHora()%></small>
                </div>
              </div> <img src="img/publicacao.jpg" class="img-fluid">
              <div class="p-2">
                <p class="text-justify"><%=p.getDescricao()%></p>
                <hr>
                <div class=" justify-content-between align-items-center">
                                  
                  <div class=" flex-row muted-color"> <span>Comentarios: </span> </div>
                </div>
                <hr>
            <% List<Comentario> listaComentarios = p.getComentarios();
                for(Comentario c : listaComentarios){
                  Usuario usu = c.getUsuario();
            %>
                <div class="comments">
                  <div class=" flex-row mb-2"> <img src="img/perfil.png" width="40" class="rounded-image">
                    <div class=" flex-column ml-2"> <span class="name"><%= usu.getNickName() %></span> <small class="comment-text"><%=c.getComentario()%></small>
                      <div class=" flex-row align-items-center status">
                        <small><%="Data: " + c.getData() + "&nbsp;&nbsp;&nbsp; Hora: " + c.getHora()%></small>
                      </div>
                    </div>
                  </div>
                </div>
                  <%
                      }
                  %>
                  <form action="SvCadastraComentario" method="post">
                    <div class="comment-input">
                      <input type="hidden" name="idPublicacao" value="<%= p.getId() %>">
                        <textarea class="form-control" name="comentario" id="comentario" cols="30" rows="2" onblur="ValidaComentario()" required></textarea>
                        <button class="btn btn-primary ml-2 mt-2" type="submit">Fazer Comentario</button>

                    </div>
                  </form>
                

            <hr>

          </div>  <!--  comment section -->
     </div> <!-- cards -->
  </div> <!-- coluna geral -->
<br>
</div> <!-- row -->
<%
}
}else{
  out.println("<br>semPublicacao, você não publicou nada ainda.");  
}
%>


</div><!-- container -->
            
      <p>
        <%= request.getAttribute("msg")==null?"":request.getAttribute("msg") %>
        </p>
              
          <br>
    </main>
    <footer>

	
	</footer>
</body>
</html>