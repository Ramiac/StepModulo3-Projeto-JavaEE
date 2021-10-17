package model.entity;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import model.persistence.PPessoa;

@Entity(name = "pessoas")
@PrimaryKeyJoinColumn(name = "id_usuario")
@DynamicUpdate(value = true)
@SelectBeforeUpdate(value = true)
@DynamicInsert(value = true)
public class Pessoa extends Usuario {

	@Column(length = 60)
	private String nome;

	@Temporal(TemporalType.DATE)
	private Date nascimento;

	@Column
	private char sexo;

	@Column(length = 11)
	private String cpf;

	@Column(length = 110)
	private String email;

	@Column(length = 11)
	private String telefone;
	
	@ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY )
	@JoinTable(name = "pessoa_enderecos", joinColumns = @JoinColumn(name = "id_pessoa"), inverseJoinColumns = @JoinColumn(name = "id_endereco"))
	private List<Endereco> listaEnderecos = new ArrayList<>();

	public Pessoa() {
	}

	public Pessoa(long id, String nickName, String senha, List<Restaurante> listaRestaurantes, List<Hotel> listaHoteis,
			List<Publicacao> listaLikesPubl, List<Comentario> listaLikesComment, String msg, String nome, Date nascimento,
			char sexo, String cpf, String email, String telefone, List<Endereco> listaEnderecos) {
		super(id, nickName, senha, listaRestaurantes, listaHoteis, listaLikesPubl, listaLikesComment, msg);
		this.nome = nome;
		this.nascimento = nascimento;
		this.sexo = sexo;
		this.cpf = cpf;
		this.email = email;
		this.telefone = telefone;
		this.listaEnderecos = listaEnderecos;
	}

	public Pessoa(String nickName, String senha, List<Restaurante> listaRestaurantes, List<Hotel> listaHoteis,
			List<Publicacao> listaLikesPubl, List<Comentario> listaLikesComment, String nome, Date nascimento, char sexo,
			String cpf, String email, String telefone, List<Endereco> listaEnderecos) {
		super(nickName, senha, listaRestaurantes, listaHoteis, listaLikesPubl, listaLikesComment);
		this.nome = nome;
		this.nascimento = nascimento;
		this.sexo = sexo;
		this.cpf = cpf;
		this.email = email;
		this.telefone = telefone;
		this.listaEnderecos = listaEnderecos;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getNascimento() {
		return nascimento;
	}

	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Endereco> getListaEnderecos() {
		return listaEnderecos;
	}

	public void setListaEnderecos(List<Endereco> listaEnderecos) {
		this.listaEnderecos = listaEnderecos;
	}

	public boolean salvar() {
		PPessoa pp = new PPessoa();
		return pp.salvar(this);
	}

	public Pessoa localizaPeloId() {
		PPessoa pp = new PPessoa();
		return pp.localizarPeloId(this);
	}

	public boolean editar() {
		PPessoa pp = new PPessoa();
		return pp.editar(this);
	}

	public boolean deletar() {
		PPessoa pp = new PPessoa();
		return pp.deletar(this);
	}

}
