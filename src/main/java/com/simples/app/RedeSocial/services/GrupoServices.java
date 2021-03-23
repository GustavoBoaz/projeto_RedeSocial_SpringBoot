package com.simples.app.RedeSocial.services;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simples.app.RedeSocial.models.Grupo;
import com.simples.app.RedeSocial.repositories.GrupoRepository;


@Service
public class GrupoServices {

	private @Autowired GrupoRepository repositoryGrupo;
	
	/**
	 * Retorna do banco uma lista de grupos.
	 * @param 	nomeDoGrupo tipo String
	 * @param 	areaDoTema tipo ENUM String EXATAS, HUMANAS ou BIOLÓGICA
	 * @return 	Lista com Grupos se os parametos estiverem devidamente escritos, caso contrario lista vasia
	 * @since 	1.0
	 * @author 	GustavoBoaz 
	 */
	public List<Grupo> listarGrupos(String nomeDoGrupo, String areaDoTema){
		if (areaDoTema.isEmpty()) {
			return repositoryGrupo.findAllByNomeDoGrupoContainingIgnoreCase(nomeDoGrupo);
		} else {
			return repositoryGrupo.findAllByNomeDoGrupoContainingIgnoreCase(nomeDoGrupo)
					.stream()
					.filter(grupo -> areaDoTema.equals(grupo.getAreaDoTema().toString()))
					.collect(Collectors.toList());	
		}
	}
	
}
