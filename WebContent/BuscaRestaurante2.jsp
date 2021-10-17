<%@page import="model.entity.Restaurante"%>
<%@page import="model.entity.Endereco"%>
<%@page import="model.entity.Usuario"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Busca Restaurante</title>
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
          <li><a href="SvNovaPublicacao" class="nav-link px-2 link-dark">Nova Publicação</a></li>
          <li><a href="BuscaRestaurante.jsp" class="nav-link px-2 link-secondary">Busca Restaurante</a></li>
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
      <h2>Buscar Restaurante</h2>
        <div class="row justify-content-md-center">
          <div class="col-10">
          <form>
            <label class="form-label" for="busca">Digite nome do restaurante procurrado: </label>
            <div class="row">
              <div class="col-8">
                <input class="form-control" type="text" name="busca" onblur="ValidaNome()" id="nome" required>
              </div>
              <div class="col">
              <button class="btn btn-primary" type="submit" formaction="SvBucaRestauranteNome" formmethod="POST">Procura pelo nome</button>
            </div>  
          </div>
          </form>
        <br>
          <form>
            <label class="form-label" for="busca">Digite o CNPJ do restaurante procurrado: </label>
            <div class="row">
              <div class="col-8">
              <input class="form-control" type="text" name="busca" id="cnpj" onblur="ValidaCnpj()" required>
              </div>
              <div class="col">
                <button class="btn btn-primary" type="submit" formaction="SvBucaRestauranteCnpj" formmethod="POST">Procura pelo CNPJ</button>
              </div>
            </div>
          </form>
        <br>
          <form>
            <label class="form-label" for="busca">Digite o CEP do restaurante procurrado: </label>
            <div class="row">
              <div class="col-8">
                <input class="form-control" type="text" name="busca" id="cep" onblur="ValidaCep(this)" required>
              </div>
              <div class="col">
                <button class="btn btn-primary" type="submit" formaction="SvBucaRestauranteCep" formmethod="POST">Procura pelo CEP</button>
              </div>
            </div>
            </form>
            </div>

<%
    if (request.getAttribute("status")=="else"){
    	out.print("<br><h2>" + request.getAttribute("msg") + "</h2><br>");

    }else if (request.getAttribute("status")=="ok"){
    		%>
    		
  <table class="table">
    <thead>
      <tr>
        <th scope="col">Nome</th>
        <th scope="col">CNPJ</th>
        <th scope="col">Email</th>
        <th scope="col">Telefone</th>
        <th scope="col">Opções de compra</th>
        <th scope="col">Matriz, filial etc</th>
        <th scope="col">Rua + numero</th>
        <th scope="col">Complemento</th>
        <th scope="col">CEP</th>
      </tr>
    </thead>
    <tbody>
    <% 

    for (Restaurante r : (List<Restaurante>) request.getAttribute("listaRestaurante")){
        for (Endereco e : r.getListaEnderecosPro()){
    	%>
    	<tr>
        <td>
        <a href="SvDispatchPerfisInfo?id=<%=r.getId()%>&tipoPublicacao=Publicação profissional: Restaurante"><%= r.getNome() %></a>
        </td>
        <td><%= r.getCnpj() %></td>
        <td><%= r.getEmail() %></td>
        <td><%= r.getTelefone() %></td>
        <td>
        <%  if (r.isReservar()) out.println("Reservação<br>");
            if (r.isRetirada()) out.println("Retirada no local<br>");
            if (r.isRappi()) out.println("Rappi<br>");
            if (r.isIFood()) out.println("Ifood<br>");
            if (r.isUberEats()) out.println("UberEats<br>");
         %>
        </td>
        <td><%= e.getNome() %></td>
        <td><%= e.getRua() + " " + e.getNumero() %></td>
        <td><%= e.getComplemento() %></td>
        <td><%= e.getCep() %></td>
      </tr>
    <% }
     }; %>
    </tbody>
  </table>
  <%}; %>

<p>
			<%= request.getAttribute("msg")==null?"":request.getAttribute("msg") %>
			</p>
        </div>
    </div>
</main>
<footer>


	
	</footer>
</body>
</html>