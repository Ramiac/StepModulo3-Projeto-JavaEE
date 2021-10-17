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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import model.persistence.PEndereco;

@Entity(name = "enderecos")
@DynamicUpdate(value = true)
@SelectBeforeUpdate(value = true)
@DynamicInsert(value = true)
public class Endereco {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(length = 60)
	private String nome; // casa, trabalho, sede do restaurante, adm do hotel etc...
	
	@Column(length = 120)
	private String rua;
	
	@Column()
	private int numero;
	
	@Column(length = 60)
	private String complemento;
	
	@Column(length = 8)
	private String cep;
	
	@Column(length = 2)
	private String estado;
	
	@Column(length = 60)
	private String pais;
	
	@ManyToMany (mappedBy = "listaEnderecos")
//	@JoinTable(name = "pessoa_enderecos", joinColumns = @JoinColumn(name = "id_pessoa"), inverseJoinColumns = @JoinColumn (name = "id_endereco"))
	private List<Pessoa> listaPessoas = new ArrayList<Pessoa>();
	
	@ManyToMany (mappedBy = "listaEnderecosPro")
	private List<Estabelecimento> listaEstabelecimentos = new ArrayList<>();
	
	@Transient
	private String msg;

	public Endereco() {
	}

	public Endereco(String nome, String rua, int numero, String complemento, String cep, String estado, String pais,
			List<Pessoa> listaPessoas, List<Estabelecimento> listaEstabelecimentos) {
		this.nome = nome;
		this.rua = rua;
		this.numero = numero;
		this.complemento = complemento;
		this.cep = cep;
		this.estado = estado;
		this.pais = pais;
		this.listaPessoas = listaPessoas;
		this.listaEstabelecimentos = listaEstabelecimentos;
	}

	public Endereco(long id, String nome, String rua, int numero, String complemento, String cep, String estado,
			String pais, List<Pessoa> listaPessoas, List<Estabelecimento> listaEstabelecimentos, String msg) {
		this.id = id;
		this.nome = nome;
		this.rua = rua;
		this.numero = numero;
		this.complemento = complemento;
		this.cep = cep;
		this.estado = estado;
		this.pais = pais;
		this.listaPessoas = listaPessoas;
		this.listaEstabelecimentos = listaEstabelecimentos;
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

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public List<Pessoa> getListaPessoas() {
		return listaPessoas;
	}

	public void setListaPessoas(List<Pessoa> listaPessoas) {
		this.listaPessoas = listaPessoas;
	}

	public List<Estabelecimento> getListaEstabelecimentos() {
		return listaEstabelecimentos;
	}

	public void setListaEstabelecimentos(List<Estabelecimento> listaEstabelecimentos) {
		this.listaEstabelecimentos = listaEstabelecimentos;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public boolean salvar () {
		PEndereco pe = new PEndereco();
		return pe.salvar(this);
	}
	
	public Endereco localizaPeloId() {
		PEndereco pe = new PEndereco();
		return pe.localizarPeloId(this);
	}
	
	public boolean editar() {
		PEndereco pe = new PEndereco();
			return pe.editar(this);
	}
	
	public boolean deletar() {
		PEndereco pe = new PEndereco();
		return pe.deletar(this);
	}
	
	
}
