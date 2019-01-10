package br.com.jhisolution.ong.control.service;

import br.com.jhisolution.ong.control.domain.TipoParteBloco;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing TipoParteBloco.
 */
public interface TipoParteBlocoService {

    /**
     * Save a tipoParteBloco.
     *
     * @param tipoParteBloco the entity to save
     * @return the persisted entity
     */
    TipoParteBloco save(TipoParteBloco tipoParteBloco);

    /**
     * Get all the tipoParteBlocos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<TipoParteBloco> findAll(Pageable pageable);
    /**
     * Get all the TipoParteBlocoDTO where ParteBloco is null.
     *
     * @return the list of entities
     */
    List<TipoParteBloco> findAllWhereParteBlocoIsNull();


    /**
     * Get the "id" tipoParteBloco.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<TipoParteBloco> findOne(Long id);

    /**
     * Delete the "id" tipoParteBloco.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
