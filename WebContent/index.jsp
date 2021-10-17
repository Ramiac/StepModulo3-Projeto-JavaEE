<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>InstaFood - Login</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj" crossorigin="anonymous"></script>

    <link href="css/signin.css" rel="stylesheet">
</head>
<body class="text-center">
    <main class="form-signin">
        <form action="SvLogin" method="POST">
            <img class="mb-4" src="img/Logo_Instafood.png" alt="" width="72" height="72">
            <h1 class="h3 mb-3 fw-normal">Instafood - Log in</h1>

            <div class="form-floating">
                <input class="form-control" type="text" name="nickName" id="nickName" placeholder="Nome de Usúario" required>
                <label for="nickName">Usuario: </label>
            </div>

            <div class="form-floating">
                <input class="form-control" type="password" name="senha" id="senha" placeholder="senha" required>
                <label for="senha">Senha: </label>
            </div>
            
            <p>
			<%= request.getAttribute("erro")==null?"":request.getAttribute("erro") %>
			</p>
            <button class="w-100 btn btn-lg btn-primary" type="submit">Entrar</button>
        </form>
        <div>
        <p>
			<%= request.getAttribute("msg")==null?"":request.getAttribute("msg") %>
		</p>
            <a href="CadastroPessoa.html">Se cadastre</a>
        </div>
    </main>
    <footer></footer>

</body>
</html> 