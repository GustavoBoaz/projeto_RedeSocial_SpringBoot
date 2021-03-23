package com.simples.app.RedeSocial.models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long idUsuario;
	
	@NotNull(message = "Campo nome não pode ser Nulo")
	@Size(max = 20, message = "Campo deve ter no maximo 20 caracteres")
	private String nome;
	
	@NotNull(message = "Campo usuario não pode ser Nulo")
	@Size(max = 10, message = "Campo deve ter no maximo 10 caracteres")
	private String usuario;
	
	@NotNull(message = "Campo Senha não pode ser Nulo")
	@Size(min = 5, message = "Campo deve ter no minimo 5 caracteres")
	private String senha;
	
	@OneToMany(mappedBy = "usuarioCriador", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonIgnoreProperties({"gruposCriados","postsDoGrupo","usuarioCriador","usuariosInscritos"})
	private Set<Grupo> gruposCriados = new HashSet<>();
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(
			name = "inscrições",
			joinColumns = @JoinColumn(name = "inscrito_id"),
			inverseJoinColumns = @JoinColumn(name = "grupo_id"))
	@JsonIgnoreProperties({"usuariosInscritos","postsDoGrupo"})
	private Set<Grupo> gruposInscrito = new HashSet<>();
	
	@OneToMany(mappedBy = "usuarioCriador", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Post> meusPosts = new ArrayList<>();

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Set<Grupo> getGruposCriados() {
		return gruposCriados;
	}

	public void setGruposCriados(Set<Grupo> gruposCriados) {
		this.gruposCriados = gruposCriados;
	}

	public Set<Grupo> getGruposInscrito() {
		return gruposInscrito;
	}

	public void setGruposInscrito(Set<Grupo> gruposInscrito) {
		this.gruposInscrito = gruposInscrito;
	}

	public List<Post> getMeusPosts() {
		return meusPosts;
	}

	public void setMeusPosts(List<Post> meusPosts) {
		this.meusPosts = meusPosts;
	}
	
}
