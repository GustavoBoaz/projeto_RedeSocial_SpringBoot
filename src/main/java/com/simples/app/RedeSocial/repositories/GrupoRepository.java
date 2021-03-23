package com.simples.app.RedeSocial.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.simples.app.RedeSocial.models.Grupo;

@Repository
public interface GrupoRepository extends JpaRepository<Grupo, Long>{

	/**
	 * Retorna do banco uma lista de grupos.
	 * @param 	nomeDoGrupo tipo String
	 * @return 	Lista com Grupos se os parametos estiverem devidamente escritos, caso contrario lista vasia
	 * @since 	1.0
	 * @author 	GustavoBoaz 
	 */
	List<Grupo> findAllByNomeDoGrupoContainingIgnoreCase(String nomeDoGrupo);
}
