package com.simples.app.RedeSocial.contorllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.simples.app.RedeSocial.models.Grupo;
import com.simples.app.RedeSocial.models.Usuario;
import com.simples.app.RedeSocial.services.UsuarioServices;

@RestController
@RequestMapping("/api/v1/usuario")
@CrossOrigin("*")
public class UsuarioController {

	private @Autowired UsuarioServices services;
	
	@PostMapping("/novo")
	public ResponseEntity<?> cadastrarUsuario(@Valid @RequestBody Usuario novoUsuario){
		Optional<Usuario> usuarioCriado = services.cadastrarUsuario(novoUsuario);
		if (!usuarioCriado.isEmpty()) {
			return ResponseEntity.status(HttpStatus.CREATED).body(usuarioCriado.get());
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuario ja existente no Sistema, tente outro usuario");
		}
	}
	
	@PostMapping("/{id_usuario}/novo/grupo")
	public ResponseEntity<?> cadastrarGrupo(
			@PathVariable(value = "id_usuario") Long idUsuario,
			@Valid @RequestBody Grupo novoGrupo){
		Optional<Grupo> grupoCriado = services.criarGrupo(novoGrupo, idUsuario);
		if (!grupoCriado.isEmpty()) {
			return ResponseEntity.status(HttpStatus.CREATED).body(grupoCriado.get());
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuario não existente, o grupo não foi criado");
		}
	}
	
	@PutMapping("/{id_usuario}/incricao/{id_grupo}")
	public ResponseEntity<?> inscriverAoGrupo(
			@PathVariable(value = "id_usuario") Long idUsuario,
			@PathVariable(value = "id_grupo") Long idGrupo){
		Optional<Usuario> usuarioInscrito = services.inscreverEmGrupo(idUsuario, idGrupo);
		if (!usuarioInscrito.isEmpty()) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(usuarioInscrito.get());
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuario ou grupo invalido");
		}
	}
	
	@GetMapping("/perfil")
	public ResponseEntity<?> retornaPerfil(@RequestParam(defaultValue = "") String usuario){
		Optional<Usuario> usuarioPerfil = services.visualizarPerfil(usuario);
		if (!usuarioPerfil.isEmpty()) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(usuarioPerfil.get());
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuario inesistente");
		}
	}
}
