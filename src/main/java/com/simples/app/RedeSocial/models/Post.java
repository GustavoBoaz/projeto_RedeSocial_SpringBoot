package com.simples.app.RedeSocial.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long idPost;
	
	@NotNull(message = "Campo titulo não pode ser Nulo")
	@Size(max = 20, message = "Campo deve ter no maximo 20 caracteres")
	private String titulo;
	
	@NotNull(message = "Campo descricao não pode ser Nulo")
	@Size(max = 10, message = "Campo deve ter no maximo 10 caracteres")
	private String descricao;
	
	private String urlImagem;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "criador")
	private Usuario usuarioCriador;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "grupo")
	private Grupo grupoCriador;

	public Long getIdPost() {
		return idPost;
	}

	public void setIdPost(Long idPost) {
		this.idPost = idPost;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getUrlImagem() {
		return urlImagem;
	}

	public void setUrlImagem(String urlImagem) {
		this.urlImagem = urlImagem;
	}

	public Usuario getUsuarioCriador() {
		return usuarioCriador;
	}

	public void setUsuarioCriador(Usuario usuarioCriador) {
		this.usuarioCriador = usuarioCriador;
	}

	public Grupo getGrupoCriador() {
		return grupoCriador;
	}

	public void setGrupoCriador(Grupo grupoCriador) {
		this.grupoCriador = grupoCriador;
	}
}
