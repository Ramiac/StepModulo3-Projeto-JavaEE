package model.entity;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import model.persistence.PPessoa;
import model.persistence.PPublicacao;

@Entity(name = "publicacoes")
@DynamicUpdate(value = true)
@SelectBeforeUpdate(value = true)
@DynamicInsert(value = true)
public class Publicacao { 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne
	@JoinColumn(name = "id_usuario", foreignKey = @ForeignKey(name = "fk_publicacao_usuario"))
	private Usuario usuario; //usuario que publicou (vem do session)

	@ManyToOne
	@JoinColumn(name = "id_restaurante", foreignKey = @ForeignKey(name = "fk_publicacao_restaurante"))
	private Restaurante restaurante; // caso o usuario tem restaurante. 
	//(check if usuario tem restaurante e oferecer escolha)
	
	@ManyToOne
	@JoinColumn(name = "id_hotel", foreignKey = @ForeignKey(name = "fk_publicacao_hotel"))
	private Hotel hotel; // caso o usuario tem hotel
	//(check if usuario tem hotel e oferecer escolha)
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "publicacoes_likes", joinColumns = @JoinColumn(name = "id_publicacao"),
	inverseJoinColumns = @JoinColumn(name = "id_usuario"))
	private List<Usuario> likes = new ArrayList<>(); // vem depois...

	@Column(length = 150)
	private String descricao;

	@Temporal(TemporalType.DATE)
	private Date data;

	@Temporal(TemporalType.TIME)
	private Date hora;

	@OneToMany(mappedBy = "publicacao", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Comentario> comentarios = new ArrayList<>(); // vem depois...

	@Transient
	private String msg;

	public Publicacao() {
	}
	
	// if Restaurante e Hotel == null : publicacao pessoal. caso nao, 
	// publicacao profissional com ID do usuario que fez a publicacao
	public Publicacao(Usuario usuario, Restaurante restaurante, Hotel hotel, List<Usuario> likes,
			String descricao, Date data, Date hora, List<Comentario> comentarios) {
		this.usuario = usuario;
		this.restaurante = restaurante;
		this.hotel = hotel;
		this.likes = likes;
		this.descricao = descricao;
		this.data = data;
		this.hora = hora;
		this.comentarios = comentarios;
	}

	public Publicacao(long id, Usuario usuario, Restaurante restaurante, Hotel hotel,
			List<Usuario> likes, String descricao, Date data, Date hora, List<Comentario> comentarios, String msg) {
		this.id = id;
		this.usuario = usuario;
		this.restaurante = restaurante;
		this.hotel = hotel;
		this.likes = likes;
		this.descricao = descricao;
		this.data = data;
		this.hora = hora;
		this.comentarios = comentarios;
		this.msg = msg;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Restaurante getRestaurante() {
		return restaurante;
	}

	public void setRestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public List<Usuario> getLikes() {
		return likes;
	}

	public void setLikes(List<Usuario> likes) {
		this.likes = likes;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Date getHora() {
		return hora;
	}

	public void setHora(Date hora) {
		this.hora = hora;
	}

	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public boolean salvar() {
		PPublicacao pp = new PPublicacao();
		return pp.salvar(this);
	}

	public Publicacao localizaPeloId() {
		PPublicacao pp = new PPublicacao();
		return pp.localizarPeloId(this);
	}

	public boolean editar() {
		PPublicacao pp = new PPublicacao();
		return pp.editar(this);
	}

	public boolean deletar() {
		PPublicacao pp = new PPublicacao();
		return pp.deletar(this);
	}
	
	public List<Publicacao> listaDezPublicacoes(){
		PPublicacao pp = new PPublicacao();
		return pp.listaDezPublicacoes(this);
	}
	
	public List<Publicacao> listaPublicacoesUsuario(Usuario u){
		PPublicacao pp = new PPublicacao();
		return pp.listaPublicacoesUsuario(this, u);
	}
	
	public List<Publicacao> listaPublicacoesInfoUsuario(Usuario u){
		PPublicacao pp = new PPublicacao();
		return pp.listaPublicacoesInfoUsuario(this, u);
	}
	
	public List<Publicacao> listaPublicacoesInfoRestaurante(Restaurante r){
		PPublicacao pp = new PPublicacao();
		return pp.listaPublicacoesInfoRestaurante(this, r);
	}
	
	public List<Publicacao> listaPublicacoesInfoHotel(Hotel h){
		PPublicacao pp = new PPublicacao();
		return pp.listaPublicacoesInfoHotel(this, h);
	}
	
	
 	
	
}
