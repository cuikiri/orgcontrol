package br.com.jhisolution.ong.control.service;

import br.com.jhisolution.ong.control.domain.TipoCurso;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing TipoCurso.
 */
public interface TipoCursoService {

    /**
     * Save a tipoCurso.
     *
     * @param tipoCurso the entity to save
     * @return the persisted entity
     */
    TipoCurso save(TipoCurso tipoCurso);

    /**
     * Get all the tipoCursos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<TipoCurso> findAll(Pageable pageable);


    /**
     * Get the "id" tipoCurso.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<TipoCurso> findOne(Long id);

    /**
     * Delete the "id" tipoCurso.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
