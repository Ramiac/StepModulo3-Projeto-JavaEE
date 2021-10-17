
function ValidaNickName() {
	let nickName = document.getElementById("nickName").value;
	if (nickName.length < 3 || nickName.length >= 60) {
		alert("Nickname precisa ter entre 3 e 60 carateres.");
	}
}

function ValidaSenha1() {
	let senha1 = document.getElementById("senha1").value;
	if (senha1.length < 3 || senha1.length >= 60) {
		alert("Senha precisa ter entre 3 e 60 carateres.");
	}
}

function ValidaSenha2() {
	let senha1 = document.getElementById("senha1").value;
	let senha2 = document.getElementById("senha2").value;
	if (senha1 !== senha2) {
		alert("Senha 1 e 2 não são iguas.")
	}
}

function ValidaNome() {
	var nome = document.getElementById("nome").value;
	if (nome.length < 3 || nome.length > 60) {
		alert("Nome precisa ter entre 3 e 60 carateres.");
		return false;
	}else{
		return true;
	}
}

function ValidaSexo() {
	let masculino = document.getElementById("masculino");
	let feminino = document.getElementById("feminino");
	let outro = document.getElementById("outro");
	if (!masculino.checked && !feminino.checked && !outro.checked) {
		alert("Selecione seu Sexo");
		return false;
	} else {
		return true;
	}
}

function ValidaNascimento() {
	let nascimento = document.getElementById("nascimento").value;

	if (new Date(nascimento).getTime() >= new Date().getTime()) {
		alert("Data de Nascimento... ainda não nasci mesmo???");
	}
}

function ValidaCpf() {
	let cpf = document.getElementById("cpf").value;
	if (isNaN(cpf) || cpf.length !== 11) {
		alert("Cpf so pode ter 11 numeros");
	}
}

function ValidaEmail() {
	let email = document.getElementById("email").value;
	let emailReg = /^\w+@[a-zA-Z_]+?\.[a-zA-Z]{2,3}$/;
	if (!email.match(emailReg)) {
		alert("Email inválido!");
	}
}

function ValidaTelefone() {
	let telefone = document .getElementById("telefone").value;
	if (isNaN(telefone) || telefone.length !== 11) {
		alert("telefone so pode ter 11 numeros");
	}
}

function ValidaCnpj() {
	let cnpj = document.getElementById("cnpj").value;
	if (cnpj.length !== 14 || isNaN(cnpj)) {
		alert("CNPJ so pode ter 14 numeros");
		return false;
	}else{
		return true;
	}
}

function ValidaDescricao() {
	let descricao = document.getElementById("descricao").value;

	if (descricao.length < 20 || descricao.length >= 150) {
		alert("Descricao precisa ter entre 20 e 150 carateres.");
	}
}

function ValidaComentario() {
	let descricao = document.getElementById("descricao").value;

	if (descricao.length < 20 || descricao.length >= 150) {
		alert("Descricao precisa ter entre 20 e 150 carateres.");
	}
}

function ValidaOpcaoCompraHotel() {
	let direito = document.getElementById("direito");
	let agencia = document.getElementById("agencia");
	let booking = document.getElementById("booking");
	let tripadvisor = document.getElementById("tripadvisor");
	if (!direito.checked && !agencia.checked && !booking.checked && !tripadvisor.checked) {
		alert("Precisa escolher pelo menos uma opção de compra");
		return false;
	} else {
		return true;
	}
}

function ValidaOpcaoCompraRestaurante() {
	let reservar = document.getElementById("reservar");
	let retirada = document.getElementById("retirada");
	let rappi = document.getElementById("rappi");
	let ifood = document.getElementById("ifood");
	let ubereats = document.getElementById("ubereats");
	if (!reservar.checked && !retirada.checked && !rappi.checked && !ifood.checked && !ubereats.checked) {
		alert("Precisa escolher pelo menos uma opção de compra");
		return false;
	} else {
		return true;
	}
}


function ValidaEnderecoNome(elem) {
	let enderecoNome = elem.value;
	if (enderecoNome.length < 3 || enderecoNome.length >= 60) {
		alert("Nome do Endereço precisa ter entre 3 e 60 carateres.");
		return false;
	} else{
		return true;
	}
}

function ValidaRua(elem) {
	let rua = elem.value;
	if (rua.length < 3 || rua.length >= 120) {
		alert("rua precisa ter entre 3 e 120 carateres.");
	}
}

function ValidaNumero(elem) {
	let numero = elem.value;
	if (isNaN(numero) || numero === '') {
		alert("Numero: só numeros!");
	}
}

function ValidaComplemento(elem) {
	let complemento = elem.value;
	if (complemento.length < 3 || complemento.length >= 60) {
		alert("Complemento precisa ter entre 3 e 60 carateres.");
	}
}

function ValidaCep(elem) {
	let cep = elem.value;
	if (isNaN(cep) || cep.length !== 8) {
		alert("CEP somente 8 numeros.");
		return false;
	}else{
		return true;
	}
}

function ValidaEstado(elem) {
	let estado = elem.value;
	if (estado.length !== 2) {
		alert("Estado somente 2 letras");

	}
}

function ValidaPais(elem) {
	let pais = elem.value;
	if (pais === '') {
		alert("esqueceu o pais!");
	}
}


function ValidaPessoa() {
	if (ValidaSexo()) {
		return true;
	} else {
		return false;
	}
}

function ValidaRestaurante() {
	if (ValidaOpcaoCompraRestaurante()) {
		return true;
	} else {
		return false;
	}
}

function ValidaHotel() {
	if (ValidaOpcaoCompraHotel()) {
		return true;
	} else {
		return false;
	}
}

function EnableNovoEndereco(element){

	
	
	if (!ValidaEnderecoNome(element)){
		document.getElementById("complemento1").disabled = true;
		document.getElementById("rua1").disabled = true;
		document.getElementById("numero1").disabled = true;
		document.getElementById("cep1").disabled = true;
		document.getElementById("estado1").disabled = true;
		document.getElementById("pais1").disabled = true;

		document.getElementById("enderecoNome1").required = false;
		document.getElementById("rua1").required = false;
		document.getElementById("numero1").required = false;
		document.getElementById("cep1").required = false;
		document.getElementById("estado1").required = false;
		document.getElementById("pais1").required = false;
		
		document.getElementById("rua1").removeAttribute("onblur");
		document.getElementById("numero1").removeAttribute("onblur");
		document.getElementById("complemento1").removeAttribute("onblur");
		document.getElementById("cep1").removeAttribute("onblur");
		document.getElementById("estado1").removeAttribute("onblur");
		document.getElementById("pais1").removeAttribute("onblur");
	}else if (ValidaEnderecoNome(element)){
		
		document.getElementById("complemento1").disabled = false;
		document.getElementById("rua1").disabled = false;
		document.getElementById("numero1").disabled = false;
		document.getElementById("cep1").disabled = false;
		document.getElementById("estado1").disabled = false;
		document.getElementById("pais1").disabled = false;

		document.getElementById("enderecoNome1").required = true;
		document.getElementById("rua1").required = true;
		document.getElementById("numero1").required = true;
		document.getElementById("cep1").required = true;
		document.getElementById("estado1").required = true;
		document.getElementById("pais1").required = true;
		
		document.getElementById("rua1").setAttribute("onblur", "ValidaRua(this)");
		document.getElementById("numero1").setAttribute("onblur", "ValidaNumero(this)");
		document.getElementById("complemento1").setAttribute("onblur", "ValidaComplemento(this)");
		document.getElementById("cep1").setAttribute("onblur", "ValidaCep(this)");
		document.getElementById("estado1").setAttribute("onblur", "ValidaEstado(this)");
		document.getElementById("pais1").setAttribute("onblur", "ValidaPais(this)");
	}
}

