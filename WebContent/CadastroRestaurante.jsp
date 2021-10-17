<%@page import="model.entity.Usuario"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>InstaFood - Cadastro de Restaurante</title>
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
    <h2>Cadastra Restaurante</h2>
    <div class="row justify-content-md-center">
      <div class="col-lg-6">
        <form action="SvCadastraRestaurante" method="POST" onsubmit="return ValidaRestaurante()">
          <div class="row g-3">
            <div class="col-sm-12">
              <label class="form-label" for="nome">Nome do Restaurante</label>
              <input class="form-control" type="text" name="nome" id="nome" onblur="ValidaNome()" required>
            </div>
            <div class="col-sm-6">
              <label class="form-label" for="cnpj">CNPJ</label>
              <input class="form-control" type="text" name="cnpj" id="cnpj" onblur="ValidaCnpj()" required>
            </div>
            <div class="col-sm-6">
              <label class="form-label" for="telefone">Telefone</label>
              <input class="form-control" type="text" name="telefone" id="telefone" onblur="ValidaTelefone()" required>
            </div>
            <div class="col-sm-12">
              <label class="form-label" for="email">Email</label>
              <input class="form-control" type="text" name="email" id="email" onblur="ValidaEmail()" required>
            </div>
            <div class="col-sm-12">
              <label class="form-label">Carrega a foto principal do Hotel: </label>
              <br>
              <img src="img/restaurante.jpg" alt="image restaurante" id="imagem" width="200" height="200">
            </div>
            <div class="col-sm-12">
              <label class="form-label" for="descricao">Descrição (max 450 charateres)</label>
              <textarea class="form-control" name="descricao" id="descricao" cols="30" rows="10" onblur="ValidaDescricao()" required></textarea>
            </div>
            <div class="col-sm-12">
              <p class="fw-bold">Opções de compra: </p>
              <div class="row">
                <div class="col">
                  <div class="form-check">
                    <input class="form-check-input" type="checkbox" name="opcaoCompra" value="reservar" id="reservar">
                    <label class="form-check-label" for="reservar">Reservação de mesa</label>
                  </div>
                  <div class="form-check">
                    <input class="form-check-input" type="checkbox" name="opcaoCompra" value="retirada" id="retirada">
                    <label class="form-check-label" for="retirada">Retirada no local</label>
                  </div>
                  <div class="form-check">
                    <input class="form-check-input" type="checkbox" name="opcaoCompra" value="rappi" id="rappi">
                    <label class="form-check-label" for="rappi">Rappi</label>
                  </div>
                </div>
                <div class="col">
                  <div class="form-check">
                    <input class="form-check-input" type="checkbox" name="opcaoCompra" value="ifood" id="ifood">
                    <label class="form-check-label" for="ifood">IFood</label>
                  </div>
                  <div class="form-check">
                      <input class="form-check-input" type="checkbox" name="opcaoCompra" value="ubereats" id="ubereats">
                      <label class="form-check-label" for="ubereats">UberEats</label>
                  </div>
                </div>
              </div> 
            </div>
            <h4>Endereço:</h4>
            <div class="col-sm-12">
            <label class="form-label" for="enderecoNome">Nome do endereço (Matriz, filhal, Kioske...): </label>
            <input class="form-control" type="text" name="enderecoNome" id="enderecoNome" onblur="ValidaEnderecoNome(this)" required>
            </div>
            <div class="col-sm-12">
            <label class="form-label" for="rua">Rua: </label>
            <input class="form-control" type="text" name="rua" id="rua" onblur="ValidaRua(this)" required>
            </div>
            <div class="col-sm-4">
            <label class="form-label" for="numero">Numero: </label>
            <input class="form-control" type="text" name="numero" id="numero" onblur="ValidaNumero(this)" required>
            </div>
            <div class="col-sm-8">
            <label class="form-label" for="complemento">Complemento: </label>
            <input class="form-control" type="text" name="complemento" id="complemento" onblur="ValidaComplemento(this)">
            </div>
            <div class="col-sm-4">
            <label class="form-label" for="cep">CEP: </label>
            <input class="form-control" type="text" name="cep" id="cep" onblur="ValidaCep(this)" required>
            </div>
            <div class="col-sm-3">
            <label class="form-label" for="estado">Estado: </label>
            <input class="form-control" type="text" name="estado" id="estado" onblur="ValidaEstado(this)"  required>
            </div>
            <div class="col-sm-5">
            <label class="form-label" for="pais">Pais: </label>
            <input class="form-control" type="text" name="pais" id="pais" onblur="ValidaPais(this)" required>
            </div>
            <button class="btn btn-primary" type="submit">Cadastrar</button>
            </div>
        </form>
    </div>
    </div>
    </div>
    </main>
    <footer>

    </footer>

</body>
</html>