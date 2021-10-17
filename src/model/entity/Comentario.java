package model.entity;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import com.sun.istack.NotNull;

import model.persistence.PComentario;

@Entity(name = "comentarios")
@DynamicUpdate (value = true)
@SelectBeforeUpdate (value = true)
@DynamicInsert (value = true)
public class Comentario { // data e hora!!! adicionar likes
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	@JoinColumn (name = "id_usuario", foreignKey = @ForeignKey(name = "fk_comentario_usuario"))
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn (name = "id_publicacao", foreignKey = @ForeignKey(name = "fk_comentario_publicacao"))
	private Publicacao publicacao;
	
	@Column (length = 150)
	private String comentario;
	
	@Temporal (TemporalType.DATE)
	private Date data;
	
	@Temporal (TemporalType.TIME)
	private Date hora;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "comentarios_likes", joinColumns = @JoinColumn(name = "id_comentario"),
	inverseJoinColumns = @JoinColumn(name = "id_usuario"))
	private List<Usuario> likes = new ArrayList<>(); // vem depois...
	
	@Transient
	private String msg;

	public Comentario() {
	}

	public Comentario(Usuario usuario, Publicacao publicacao, Date data, Date hora, String comentario, List<Usuario> likes) {
		this.usuario = usuario;
		this.publicacao = publicacao;
		this.data = data;
		this.hora = hora;
		this.comentario = comentario;
		this.likes = likes;
	}

	public Comentario(long id, Usuario usuario, Publicacao publicacao, Date data, Date hora, String comentario, List<Usuario> likes, String msg) {
		this.id = id;
		this.usuario = usuario;
		this.publicacao = publicacao;
		this.data = data;
		this.hora = hora;
		this.comentario = comentario;
		this.msg = msg;
		this.likes = likes;
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

	public Publicacao getPublicacao() {
		return publicacao;
	}

	public void setPublicacao(Publicacao publicacao) {
		this.publicacao = publicacao;
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

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public List<Usuario> getLikes() {
		return likes;
	}

	public void setLikes(List<Usuario> likes) {
		this.likes = likes;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public boolean salvar () {
		PComentario pc = new PComentario();
		return pc.salvar(this);
	}
	
	public Comentario localizaPeloId() {
		PComentario pc = new PComentario();
		return pc.localizarPeloId(this);
	}
	
	public boolean editar() {
		PComentario pc = new PComentario();
			return pc.editar(this);
	}
	
	public boolean deletar() {
		PComentario pc = new PComentario();
		return pc.deletar(this);
	}
	
}
