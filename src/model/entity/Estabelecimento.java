package model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DynamicUpdate (value = true)
@SelectBeforeUpdate (value = true)
@DynamicInsert (value = true)
public class Estabelecimento { 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(length = 60)
	private String nome;
	
	@Column(length = 14)
	private String cnpj; 
	
	@Column(length = 110)
	private String email;

	@Column(length = 11)
	private String telefone;
	
	@ManyToMany (cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "estabelecimento_enderecos",
	joinColumns = @JoinColumn(name = "id_estabelecimento"),
	inverseJoinColumns = @JoinColumn(name = "id_endereco")
		)
	private List<Endereco> listaEnderecosPro = new ArrayList<>();
	
	@Column (length = 150)
	private String descricao;
	
	@Transient
	private String msg;

	public Estabelecimento() {
	}

	public Estabelecimento(String nome, String cnpj, String email, String telefone, List<Endereco> listaEnderecosPro,
			String descricao) {
		this.nome = nome;
		this.cnpj = cnpj;
		this.email = email;
		this.telefone = telefone;
		this.listaEnderecosPro = listaEnderecosPro;
		this.descricao = descricao;
	}

	public Estabelecimento(long id, String nome, String cnpj, String email, String telefone,
			List<Endereco> listaEnderecosPro, String descricao, String msg) {
		this.id = id;
		this.nome = nome;
		this.cnpj = cnpj;
		this.email = email;
		this.telefone = telefone;
		this.listaEnderecosPro = listaEnderecosPro;
		this.descricao = descricao;
		this.msg = msg;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public List<Endereco> getListaEnderecosPro() {
		return listaEnderecosPro;
	}

	public void setListaEnderecosPro(List<Endereco> listaEnderecosPro) {
		this.listaEnderecosPro = listaEnderecosPro;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	

}
