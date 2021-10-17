package model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import model.persistence.PRestaurante;

@Entity
@PrimaryKeyJoinColumn(name = "id_estabelecimento")
@DynamicUpdate(value = true)
@SelectBeforeUpdate(value = true)
@DynamicInsert(value = true)
public class Restaurante extends Estabelecimento {
	
	@ManyToMany (cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable (name = "usuario_restaurante",
		joinColumns = @JoinColumn(name = "id_restaurante"),
		inverseJoinColumns = @JoinColumn(name = "id_usuario")
			)
	private List<Usuario> listaUsuarios = new ArrayList<>(); // gerentes

	@Column
	private boolean reservar;
	@Column
	private boolean retirada;
	@Column
	private boolean rappi;
	@Column
	private boolean iFood;
	@Column
	private boolean uberEats;

	public Restaurante() {
		super();
	}

	public Restaurante(long id, List<Usuario> listaUsuarios, String nome, String cnpj, String email, String telefone, List<Endereco> listaEnderecosPro,
			String descricao, boolean reservar, boolean retirada, boolean rappi, boolean iFood, boolean uberEats,
			String msg) {
		super(id, nome, cnpj, email, telefone, listaEnderecosPro, descricao, msg);
		this.listaUsuarios = listaUsuarios;
		this.reservar = reservar;
		this.retirada = retirada;
		this.rappi = rappi;
		this.iFood = iFood;
		this.uberEats = uberEats;
	}
	
	public Restaurante(List<Usuario> listaUsuarios, String nome, String cnpj, String email, String telefone, List<Endereco> listaEnderecosPro,
			String descricao, boolean reservar, boolean retirada, boolean rappi, boolean iFood, boolean uberEats) {
		super(nome, cnpj, email, telefone, listaEnderecosPro, descricao);
		this.listaUsuarios = listaUsuarios;
		this.reservar = reservar;
		this.retirada = retirada;
		this.rappi = rappi;
		this.iFood = iFood;
		this.uberEats = uberEats;
	}

	public List<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}

	public void setListaUsuarios(List<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

	public boolean isReservar() {
		return reservar;
	}

	public void setReservar(boolean reservar) {
		this.reservar = reservar;
	}

	public boolean isRetirada() {
		return retirada;
	}

	public void setRetirada(boolean retirada) {
		this.retirada = retirada;
	}

	public boolean isRappi() {
		return rappi;
	}

	public void setRappi(boolean rappi) {
		this.rappi = rappi;
	}

	public boolean isIFood() {
		return iFood;
	}

	public void setIFood(boolean iFood) {
		this.iFood = iFood;
	}

	public boolean isUberEats() {
		return uberEats;
	}

	public void setUberEats(boolean uberEats) {
		this.uberEats = uberEats;
	}

	public boolean salvar() {
		PRestaurante pr = new PRestaurante();
		return pr.salvar(this);
	}

	public Restaurante localizaPeloId() {
		PRestaurante pr = new PRestaurante();
		return pr.localizarPeloId(this);
	}

	public boolean editar() {
		PRestaurante pr = new PRestaurante();
		return pr.editar(this);
	}

	public boolean deletar() {
		PRestaurante pr = new PRestaurante();
		return pr.deletar(this);
	}
	
	public List<Restaurante> localizarPeloNomeList() {
		PRestaurante pr = new PRestaurante();
		return pr.localizarPeloNomeList(this);
	}
	
	public List<Restaurante> localizarPeloCnpjList(){
		PRestaurante pr = new PRestaurante();
		return pr.localizarPeloCnpjList(this);
	}
	
	public List<Restaurante> localizarPeloCepList(Endereco e){
		PRestaurante pr = new PRestaurante();
		return pr.localizarPeloCepList(this, e);
	}
	
	public List<Restaurante> localizarPeloUsuarioList(Usuario u){
		PRestaurante pr = new PRestaurante();
		return pr.localizarPeloUsuarioList(this, u);
	}
}
