<%@page import="model.entity.Usuario"%>
<%@page import="model.entity.Restaurante"%>
<%@page import="model.entity.Hotel"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Instafood - Selecione seu estabelicmento</title>
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
      <div class="container">
    <h2>Selecione seu establecimento a editar</h2>
    <div class="row justify-content-md-center">
      <div class="col-lg-6">
        <form action="SvSelecioneEstabelecimento" method="POST">
            
            <%  
                List<Restaurante> listaRestaurantes = (List<Restaurante>) request.getAttribute("listaRestaurantes");
                if (listaRestaurantes != null){
                out.print("<span class='fw-bold'>Restaurantes: </span>");
                for (Restaurante r : listaRestaurantes){
            %>
            <div class="form-check">
            <input class="form-check-input" type="radio" name="estabelecimento" id="<%=r.getNome()%>" value="<%=r.getId()%>" onclick="tipoRestaurante()">
            <label class="form-check-label" for="<%=r.getNome()%>"><%=r.getNome()%></label>
            </div>
            <%
                } 
            }
            %>
            <br>
            <% List<Hotel> listaHoteis = (List<Hotel>) request.getAttribute("listaHoteis"); 
                if (listaHoteis != null){
                out.print("<span class='fw-bold'>Hoteis: </span>");
                for (Hotel h : listaHoteis){
            %>
            <div class="form-check">
            <input class="form-check-input" type="radio" name="estabelecimento" id="<%=h.getNome()%>" value="<%=h.getId()%>" onclick="tipoHotel()">
            <label class="form-check-label" for="<%=h.getNome()%>"><%=h.getNome()%></label>
            </div>
            <%
                } 
            }
            %>
            <br>
            <input type="hidden" name="tipoEstabelecimento" id="tipoEstabelecimento" value="">
            <button class="btn btn-primary" type="submit">Editar o estabelecimento selecionado</button>
        </form>
        </div>
        </div>
      </div>
    </main>
    <footer>

    </footer>

</body>
<script>
    function tipoRestaurante (){
        document.getElementById("tipoEstabelecimento").value = 'restaurante';
    }

    function tipoHotel (){
        document.getElementById("tipoEstabelecimento").value = 'hotel';
    }
</script>

</html>