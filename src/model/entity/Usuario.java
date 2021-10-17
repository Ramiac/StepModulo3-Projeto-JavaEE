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
import javax.persistence.ManyToMany;
import javax.persistence.Transient;
import javax.transaction.Transactional;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import model.persistence.PUsuario;

@Entity(name = "usuarios")
@Inheritance(strategy = InheritanceType.JOINED)
@DynamicUpdate(value = true)
@SelectBeforeUpdate(value = true)
@DynamicInsert(value = true)
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(length = 60)
	private String nickName;

	@Column(length = 60)
	private String senha;

	@ManyToMany(mappedBy = "listaUsuarios")
	private List<Restaurante> listaRestaurantes = new ArrayList<>();

	@ManyToMany(mappedBy = "listaUsuarios")
	private List<Hotel> listaHoteis = new ArrayList<>();

	@ManyToMany(mappedBy = "likes")
	private List<Publicacao> listaLikesPubl = new ArrayList<>();

	@ManyToMany(mappedBy = "likes")
	private List<Comentario> listaLikesComment = new ArrayList<>();

	@Transient
	private String msg;

	public Usuario() {
	}

	public Usuario(long id, String nickName, String senha, List<Restaurante> listaRestaurantes, List<Hotel> listaHoteis,
			List<Publicacao> listaLikesPubl, List<Comentario> listaLikesComment, String msg) {
		this.id = id;
		this.nickName = nickName;
		this.senha = senha;
		this.listaRestaurantes = listaRestaurantes;
		this.listaHoteis = listaHoteis;
		this.listaLikesPubl = listaLikesPubl;
		this.listaLikesComment = listaLikesComment;
		this.msg = msg;
	}

	public Usuario(String nickName, String senha, List<Restaurante> listaRestaurantes, List<Hotel> listaHoteis,
			List<Publicacao> listaLikesPubl, List<Comentario> listaLikesComment) {
		this.nickName = nickName;
		this.senha = senha;
		this.listaRestaurantes = listaRestaurantes;
		this.listaRestaurantes = listaRestaurantes;
		this.listaLikesPubl = listaLikesPubl;
		this.listaLikesComment = listaLikesComment;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Restaurante> getListaRestaurantes() {
		return listaRestaurantes;
	}

	public void setListaRestaurantes(List<Restaurante> listaRestaurantes) {
		this.listaRestaurantes = listaRestaurantes;
	}

	public List<Hotel> getListaHoteis() {
		return listaHoteis;
	}

	public void setListaHoteis(List<Hotel> listaHoteis) {
		this.listaHoteis = listaHoteis;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<Publicacao> getListaLikesPubl() {
		return listaLikesPubl;
	}

	public void setListaLikesPubl(List<Publicacao> listaLikesPubl) {
		this.listaLikesPubl = listaLikesPubl;
	}

	public List<Comentario> getListaLikesComment() {
		return listaLikesComment;
	}

	public void setListaLikesComment(List<Comentario> listaLikesComment) {
		this.listaLikesComment = listaLikesComment;
	}

	public boolean login(String nickName, String senha) {
		this.nickName = nickName;
		this.senha = senha;

		PUsuario pu = new PUsuario();
		return pu.login(this);
	}

	public Usuario localizaPeloId() {
		PUsuario pu = new PUsuario();
		return pu.localizarPeloId(this);
	}

}
