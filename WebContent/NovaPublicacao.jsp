<%@page import="model.entity.Usuario"%>
<%@page import="model.entity.Estabelecimento"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>InstaFood - Nova Publicacao</title>
<script src="js/valida.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj" crossorigin="anonymous"></script>
<link rel="stylesheet" href="css/geral.css">

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
          <li><a href="SvNovaPublicacao" class="nav-link px-2 link-secondary">Nova Publicação</a></li>
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
    <div class="container">
      <h2>Nova Publicação</h2>
      <div class="row justify-content-md-center">
        <div class="col-lg-6">
          <form action="SvCadastraPublicacao" method="POST">
            <% Usuario u = (Usuario) request.getSession().getAttribute("usuarioLogado");
                if (u.getListaRestaurantes() != null && u.getListaHoteis() !=null){
                    %>
                    <h4>Tipo de Publicação: </h4>
                    <input class="form-check-input" type="radio" name="tipoPublicacao" id="pessoal" value="pessoal" checked>
                    <label class="form-check-label" for="pessoal">Pessoal</label><br>
                    <%
                        if (u.getListaRestaurantes().size() != 0){
                            out.println("<br><p class='fw-bold'>Restaurantes: </p>");
                            List <Estabelecimento> restaurantes = (List<Estabelecimento>) request.getSession().getAttribute("restaurantes");
                            for(Estabelecimento e: restaurantes){
                                %>
                                <input class="form-check-input" type="radio" name="tipoPublicacao" id="<%= e.getId() %>" value="<%= e.getId() %>">
                                <label for="<%= e.getId() %>" class="form-check-label"><%= e.getNome() %></label><br>
                                <%
                            }
                        };
                        if (u.getListaHoteis().size() != 0){
                            out.println("<br><p class='fw-bold'>Hoteis: </p>");
                            List <Estabelecimento> hoteis = (List<Estabelecimento>) request.getSession().getAttribute("hoteis");
                            for(Estabelecimento e: hoteis){
                                %>
                                <input class="form-check-input" type="radio" name="tipoPublicacao" id="<%= e.getId() %>" value="<%= e.getId() %>">
                                <label for="<%= e.getId() %>" class="form-check-label"><%= e.getNome() %></label><br>
                                <%
                            }
                        } ;  
                } else request.setAttribute("tipoPublicacao", "pessoal");
            %>
            <br>
            <label class="form-label">Carrega a foto da publicacao: </label> <br>
            <img src="img/publicacao.jpg" alt="" id="imagem" width="200" height="200">
            <br>
            <label for="descricao" class="form-label">Descrição (max 450 charateres): </label>
            <br>
            <textarea class="form-control" name="descricao" id="descricao" cols="30" rows="5" onblur="ValidaDescricao()" required></textarea>
            <br>
            <button class="btn btn-primary" type="submit">Publicar</button>
        </form>
        </div>
      </div>
        
      </div>
    </main>
    <footer>

    </footer>

</body>
</html>