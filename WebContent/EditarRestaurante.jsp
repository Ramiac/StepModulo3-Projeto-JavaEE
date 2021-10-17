<%@page import="model.entity.Usuario"%>
<%@page import="model.entity.Restaurante"%>
<%@page import="model.entity.Endereco"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Instafood - Editar Restaurante</title>
  <script src="js/valida.js"></script>
  <script src="js/setNovoEndereco.js"></script>
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
    <h2>Editar restaurante</h2>
    <div class="row justify-content-md-center">
      <div class="row">
        <div class="col-lg-6 offset-lg-3">
          <form>
          <p><span class="fw-bold">Nome do restaurante: </span><%= request.getAttribute("nome") %></p>
          <p><span class="fw-bold">CNPJ: </span><%= request.getAttribute("cnpj") %></p>
          
          <label class="form-label" for="email">Email: </label>
          <input class="form-control" type="text" name="email" id="email" onblur="ValidaEmail()" required value="<%= request.getAttribute("email") %>">
          <br>
          <label class="form-label" for="telefone">Telefone: </label>
          <input class="form-control" type="text" name="telefone" id="telefone" onblur="ValidaTelefone()" required  value="<%= request.getAttribute("telefone") %>">
          <br>
          <label class="form-label" for="descricao">Descricao : </label>
          <textarea class="form-control" name="descricao" id="descricao" cols="30" rows="10" onblur="ValidaDescricao()" required ><%=request.getAttribute("descricao")%></textarea>
          <br>
          <p class="fw-bold">Opções de compra: </p>
          <div class="row">
            <div class="col-sm-6">
              <div class="form-check">
                <input class="form-check-input" type="checkbox" name="opcaoCompra" value="reservar" id="reservar"
                <%
                if ((boolean)request.getAttribute("reservar")) out.print(" checked ");
                %>>
                <label class="form-check-label" for="reservar">Reservação de mesa</label>
              </div>
              <div class="form-check">
                <input class="form-check-input" type="checkbox" name="opcaoCompra" value="retirada" id="retirada"
                <%
                if((boolean)request.getAttribute("retirada"))out.print(" checked ");
                %>>
                <label class="form-check-label" for="retirada">Retirada no local</label>
              </div>
              <div class="form-check">
                <input class="form-check-input" type="checkbox" name="opcaoCompra" value="rappi" id="rappi"
                <%
                if((boolean)request.getAttribute("rappi"))out.print(" checked ");
                %>>
                <label class="form-check-label" for="rappi">Rappi</label>
              </div>
            </div>
            <div class="col-sm-6">
              <div class="form-check">
                <input class="form-check-input" type="checkbox" name="opcaoCompra" value="ifood" id="ifood"
                <%
                if ((boolean)request.getAttribute("iFood"))out.print(" checked ");
                %>>
                <label class="form-check-label" for="ifood">IFood</label>
              </div>
              <div class="form-check">
                <input class="form-check-input" type="checkbox" name="opcaoCompra" value="ubereats" id="ubereats"
                <%
                if((boolean)request.getAttribute("uberEats"))out.print(" checked ");
                %>>
                <label class="form-check-label" for="ubereats">UberEats</label>
              </div>
            </div>
          </div>
          <br>
    <label class="form-label">Carrega a foto principal do Restaurante: </label>
    <br>
    <img src="img/restaurante.jpg" alt="" id="imagem" width="200" height="200">
    <br>

    <h4>Cadastrar novo Endereço:</h4>
    <div class="row g-3">
      <div class="col-sm-12">
        <label class="form-label" for="enderecoNome">Nome do endereço (Matriz, filial...):
        </label>
        <input class="form-control" type="text" name="enderecoNome" id="enderecoNome1" onblur="EnableNovoEndereco(this)">
      </div>
      <div class="col-sm-12">
        <label class="form-label" for="rua">Rua: </label>
        <input class="form-control" type="text" name="rua" id="rua1" disabled>
      </div>
      <div class="col-sm-4">
        <label class="form-label" for="numero">Numero: </label>
        <input class="form-control" type="number" name="numero" id="numero1" disabled>
      </div>
      <div class="col-sm-8">
        <label class="form-label" for="complemento">Complemento: </label>
        <input class="form-control" type="text" name="complemento" id="complemento1" disabled>
      </div>
      <div class="col-sm-4">
        <label class="form-label" for="cep">CEP: </label>
        <input class="form-control" type="text" name="cep" id="cep1" disabled>
      </div>
      <div class="col-sm-3">
        <label class="form-label" for="estado">Estado: </label>
        <input class="form-control" type="text" name="estado" id="estado1" disabled>
      </div>
      <div class="col-sm-5">
        <label class="form-label" for="pais">Pais: </label>
        <input class="form-control" type="text" name="pais" id="pais1" disabled>
      </div>
    </div>

        <button class="btn btn-primary" type="submit" formaction="SvEditarRestaurante" formmethod="POST" onsubmit="return ValidaRestaurante()"> Editar </button>
        <button class="btn btn-primary" type="submit" formaction="SvDeletarRestaurante" formmethod="POST"> Deletar </button>
      </form>
      
    </div>
  </div>
</div>
<div class="col-lg-12 mt-4">
  <h3>Editar seus endereços:</h3>

    <% if (request.getAttribute("listaEnderecos") != null){%>
<table class="table">
    <thead>
        <tr>
            <th>Nome</th>
            <th>Rua</th>
            <th>Numero</th>
            <th>Complemento</th>
            <th>CEP</th>
            <th>Estado</th>
            <th>Pais</th>
            <th>Action</th>
        </tr>

    </thead>
    <tbody>
        <% List <Endereco> listaEnderecos = (List <Endereco>) request.getAttribute("listaEnderecos");
        for (Endereco e : listaEnderecos){

        %>
        <form>
        
            <tr>
                <input type="hidden" name="id" value="<%= e.getId() %>">
                <td><input class="form-control" type="text" name="nome" id="nome" value="<%= e.getNome() %>" onblur="ValidaEnderecoNome(this)" required>
                </td>
                <td><input class="form-control" type="text" name="rua" id="rua" value="<%= e.getRua() %>" onblur="ValidaRua(this)" required>
                </td>
                <td><input class="form-control" type="text" name="numero" id="numero" value="<%= e.getNumero() %>" onblur="ValidaNumero(this)" required>
                </td>
                <td><input class="form-control" type="text" name="complemento" id="complemento" value="<%= e.getComplemento() %>" onblur="ValidaComplemento(this)">
                </td>
                <td><input class="form-control" type="text" name="cep" id="cep" value="<%= e.getCep() %>" onblur="ValidaCep(this)" required>
                </td>
                <td><input class="form-control" type="text" name="estado" id="estado" value="<%= e.getEstado() %>" onblur="ValidaEstado(this)" required>
                </td>
                <td><input class="form-control" type="text" name="pais" id="pais" value="<%= e.getPais() %>" onblur="ValidaPais(this)" required>
                </td>
                <td>
                    <button class="btn btn-primary btn-sm" type="submit" formaction="SvEditarEndereco" formmethod="POST">Editar</button>
                    <button class="btn btn-primary btn-sm mt-1" type="submit" formaction="SvDeletarEndereco" formmethod="POST">Deletar</button>
                </td>
            </tr>
        
        <% }}; %>
      </form>
    </tbody>
</table>

</div>  
</div>
    
</main>
<footer>
    <br><br><br><br><br>

    </footer>
    
</body>




</html>