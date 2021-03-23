package com.simples.app.RedeSocial.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simples.app.RedeSocial.models.Grupo;
import com.simples.app.RedeSocial.models.Usuario;
import com.simples.app.RedeSocial.repositories.GrupoRepository;
import com.simples.app.RedeSocial.repositories.UsuarioRepository;

@Service
public class UsuarioServices {
	
	private @Autowired UsuarioRepository repositoryUsuario;
	private @Autowired GrupoRepository repositoryGrupo;
	
	/**
	 * Registra no banco um novo usuario para acessar o sistema caso não exista, retornando um Optional com a Entidade
	 * @param 	novoUsuario uma Entidade Usuario
	 * @return 	Optional com Usuario se os parametos estiverem devidamente escritos, caso contrario vasio(empty)
	 * @since 	1.0
	 * @author 	GustavoBoaz 
	 */
	public Optional<Usuario> cadastrarUsuario(Usuario novoUsuario){
		
		Optional<Usuario> usuarioExistente = repositoryUsuario.findByUsuario(novoUsuario.getUsuario());	
		if(usuarioExistente.isPresent()) {
			return Optional.empty();
		}
		
		Optional<Usuario> usuarioCadastrado = Optional.ofNullable(repositoryUsuario.save(novoUsuario));
		if (usuarioCadastrado.isPresent()) {
			return usuarioCadastrado;
		} else {
			return Optional.empty();
		}
	}
	
	/**
	 * Registra no banco um novo grupo vinculando ao usuario retornando um Optional com a Entidade Grupo
	 * @param 	novoGrupo uma Entidade Grupo
	 * @param 	idUsuario tipo Long
	 * @return 	Optional com Grupo se os parametos estiverem devidamente escritos, caso contrario vasio(empty)
	 * @since 	1.0
	 * @author 	GustavoBoaz 
	 */
	public Optional<Grupo> criarGrupo(Grupo novoGrupo, Long idUsuario){
		
		Optional<Usuario> usuarioExistente = repositoryUsuario.findById(idUsuario);
		if (usuarioExistente.isPresent()) {
			Grupo grupoCadastrado = repositoryGrupo.save(novoGrupo);
			grupoCadastrado.setUsuarioCriador(usuarioExistente.get());
			return Optional.ofNullable(repositoryGrupo.save(grupoCadastrado));
		} else {
			return Optional.empty();
		}
	}
	
	/**
	 * Registra no banco uma nova inscrição do usuario em um grupo e retorna um Optional com a Entidade Usuario
	 * @param 	idGrupo tipo Long
	 * @param 	idUsuario tipo Long
	 * @return 	Optional com Usuario se os parametos estiverem devidamente escritos, caso contrario vasio(empty)
	 * @since 	1.0
	 * @author 	GustavoBoaz 
	 */
	public Optional<Usuario> inscreverEmGrupo(Long idUsuario, Long idGrupo){
		
		Optional<Grupo> grupoExistente = repositoryGrupo.findById(idGrupo);
		Optional<Usuario> usuarioExistente = repositoryUsuario.findById(idUsuario);
		if (usuarioExistente.isPresent() && grupoExistente.isPresent()) {
			usuarioExistente.get().getGruposInscrito().add(grupoExistente.get());
			return Optional.ofNullable(repositoryUsuario.save(usuarioExistente.get()));
		} else {
			return Optional.empty();
		}
	}
	
	/**
	 * Retorna do banco uma entidade do usuario no formato Optional.
	 * @param 	usuario tipo String
	 * @return 	Optional com Usuario se os parametos estiverem devidamente escritos, caso contrario vasio(empty)
	 * @since 	1.0
	 * @author 	GustavoBoaz 
	 */
	public Optional<Usuario> visualizarPerfil(String usuario){
		
		Optional<Usuario> usuarioExistente = repositoryUsuario.findByUsuario(usuario);
		if (usuarioExistente.isPresent()) {
			return usuarioExistente;
		} else {
			return Optional.empty();
		}
	}
}
