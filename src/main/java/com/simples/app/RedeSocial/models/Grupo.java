package com.simples.app.RedeSocial.models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.simples.app.RedeSocial.models.Util.Tema;

@Entity
public class Grupo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long idGrupo;
	
	@NotNull(message = "Campo nome não pode ser Nulo")
	@Size(max = 20, message = "Campo deve ter no maximo 20 caracteres")
	private String nomeDoGrupo;
	
	@NotNull(message = "Campo descrição não pode ser Nulo")
	@Size(max = 100, message = "Campo deve ter no maximo 100 caracteres")
	private String descricao;
	
	@NotNull(message = "Campo tema deve conter: EXATAS, HUMANAS ou BIOLÓGICA")
	@Enumerated(EnumType.STRING)
	private Tema areaDoTema;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "criador")
	@JsonIgnoreProperties({"gruposCriados","senha","idUsuario","usuario","gruposInscrito","meusPosts"})
	private Usuario usuarioCriador;
	
	@ManyToMany(mappedBy = "gruposInscrito", fetch = FetchType.EAGER)
	@JsonIgnoreProperties({"gruposCriados","senha","idUsuario","gruposInscrito","meusPosts"})
	private Set<Usuario> usuariosInscritos = new HashSet<>();
	
	@OneToMany(mappedBy = "grupoCriador", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Post> postsDoGrupo = new ArrayList<>();

	public Long getIdGrupo() {
		return idGrupo;
	}

	public void setIdGrupo(Long idGrupo) {
		this.idGrupo = idGrupo;
	}

	public String getNomeDoGrupo() {
		return nomeDoGrupo;
	}

	public void setNomeDoGrupo(String nomeDoGrupo) {
		this.nomeDoGrupo = nomeDoGrupo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Tema getAreaDoTema() {
		return areaDoTema;
	}

	public void setAreaDoTema(Tema areaDoTema) {
		this.areaDoTema = areaDoTema;
	}

	public Usuario getUsuarioCriador() {
		return usuarioCriador;
	}

	public void setUsuarioCriador(Usuario usuarioCriador) {
		this.usuarioCriador = usuarioCriador;
	}

	public Set<Usuario> getUsuariosInscritos() {
		return usuariosInscritos;
	}

	public void setUsuariosInscritos(Set<Usuario> usuariosInscritos) {
		this.usuariosInscritos = usuariosInscritos;
	}

	public List<Post> getPostsDoGrupo() {
		return postsDoGrupo;
	}

	public void setPostsDoGrupo(List<Post> postsDoGrupo) {
		this.postsDoGrupo = postsDoGrupo;
	}
}
