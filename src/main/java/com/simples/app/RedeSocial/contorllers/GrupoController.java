package com.simples.app.RedeSocial.contorllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.simples.app.RedeSocial.models.Grupo;
import com.simples.app.RedeSocial.services.GrupoServices;

@RestController
@RequestMapping("/api/v1/grupo")
@CrossOrigin("*")
public class GrupoController {

	private @Autowired GrupoServices services;
	
	@GetMapping("/lista")
	public ResponseEntity<?> listaDeGrupos(
			@RequestParam(defaultValue = "") String nomeDoGrupo,
			@RequestParam(defaultValue = "") String areaDoTema){
		List<Grupo> lista = services.listarGrupos(nomeDoGrupo, areaDoTema);
		if (!lista.isEmpty()) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(lista);
		} else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Grupo não existente");
		}
	}
}
