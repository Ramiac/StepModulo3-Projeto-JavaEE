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

import model.persistence.PHotel;

@Entity
@PrimaryKeyJoinColumn(name = "id_estabelecimento")
@DynamicUpdate(value = true)
@SelectBeforeUpdate(value = true)
@DynamicInsert(value = true)
public class Hotel extends Estabelecimento{
	@ManyToMany (cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable (name = "usuario_hotel",
		joinColumns = @JoinColumn(name = "id_hotel"),
		inverseJoinColumns = @JoinColumn(name = "id_usuario")
			)
	private List<Usuario> listaUsuarios = new ArrayList<>();
	
	@Column
	private boolean direito;
	@Column
	private boolean booking;
	@Column
	private boolean tripAdvisor;
	@Column
	private boolean agencia;
	

	public Hotel() {
		super();
	}

	public Hotel(long id, List<Usuario> listaUsuarios, String nome, String cnpj, String email, String telefone, List<Endereco> listaEnderecosPro,
			String descricao, boolean direito, boolean booking, boolean tripAdvisor, boolean agencia, String msg) {
		super(id, nome, cnpj, email, telefone, listaEnderecosPro, descricao, msg);
		this.listaUsuarios = listaUsuarios;
		this.direito = direito;
		this.booking = booking;
		this.tripAdvisor = tripAdvisor;
		this.agencia = agencia;
	}
	
	public Hotel(List<Usuario> listaUsuarios, String nome, String cnpj, String email, String telefone, List<Endereco> listaEnderecosPro,
			String descricao, boolean direito, boolean booking, boolean tripAdvisor, boolean agencia) {
		super(nome, cnpj, email, telefone, listaEnderecosPro, descricao);
		this.listaUsuarios = listaUsuarios;
		this.direito = direito;
		this.booking = booking;
		this.tripAdvisor = tripAdvisor;
		this.agencia = agencia;
	}

	public List<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}

	public void setListaUsuarios(List<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}
	
	public boolean isDireito() {
		return direito;
	}

	public void setDireito(boolean direito) {
		this.direito = direito;
	}

	public boolean isBooking() {
		return booking;
	}

	public void setBooking(boolean booking) {
		this.booking = booking;
	}

	public boolean isTripAdvisor() {
		return tripAdvisor;
	}

	public void setTripAdvisor(boolean tripAdvisor) {
		this.tripAdvisor = tripAdvisor;
	}

	public boolean isAgencia() {
		return agencia;
	}

	public void setAgencia(boolean agencia) {
		this.agencia = agencia;
	}

	public boolean salvar () {
		PHotel ph = new PHotel();
		return ph.salvar(this);
	}
	
	public Hotel localizaPeloId() {
		PHotel ph = new PHotel();
		return ph.localizarPeloId(this);
	}
	
	public boolean editar() {
		PHotel ph = new PHotel();
			return ph.editar(this);
	}
	
	public boolean deletar() {
		PHotel ph = new PHotel();
		return ph.deletar(this);
	}
	
	public List<Hotel> localizarPeloNomeList(){
		PHotel ph = new PHotel();
		return ph.localizarPeloNomeList(this);
	}
	
	public List<Hotel> localizarPeloCnpjList(){
		PHotel ph = new PHotel();
		return ph.localizarPeloCnpjList(this);
	}
	
	public List<Hotel> localizarPeloCepList(Endereco e){
		PHotel ph = new PHotel();
		return ph.localizarPeloCepList(this, e);
	}
	
	public List<Hotel> localizarPeloUsuarioList(Usuario u){
		PHotel ph = new PHotel();
		return ph.localizarPeloUsuarioList(this, u);
	}

}
