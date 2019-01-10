package br.com.jhisolution.ong.control.service;

import br.com.jhisolution.ong.control.domain.TipoBloco;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing TipoBloco.
 */
public interface TipoBlocoService {

    /**
     * Save a tipoBloco.
     *
     * @param tipoBloco the entity to save
     * @return the persisted entity
     */
    TipoBloco save(TipoBloco tipoBloco);

    /**
     * Get all the tipoBlocos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<TipoBloco> findAll(Pageable pageable);
    /**
     * Get all the TipoBlocoDTO where Bloco is null.
     *
     * @return the list of entities
     */
    List<TipoBloco> findAllWhereBlocoIsNull();


    /**
     * Get the "id" tipoBloco.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<TipoBloco> findOne(Long id);

    /**
     * Delete the "id" tipoBloco.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
