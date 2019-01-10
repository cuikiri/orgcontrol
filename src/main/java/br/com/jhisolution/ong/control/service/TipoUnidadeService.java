package br.com.jhisolution.ong.control.service;

import br.com.jhisolution.ong.control.domain.TipoUnidade;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing TipoUnidade.
 */
public interface TipoUnidadeService {

    /**
     * Save a tipoUnidade.
     *
     * @param tipoUnidade the entity to save
     * @return the persisted entity
     */
    TipoUnidade save(TipoUnidade tipoUnidade);

    /**
     * Get all the tipoUnidades.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<TipoUnidade> findAll(Pageable pageable);
    /**
     * Get all the TipoUnidadeDTO where Unidade is null.
     *
     * @return the list of entities
     */
    List<TipoUnidade> findAllWhereUnidadeIsNull();


    /**
     * Get the "id" tipoUnidade.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<TipoUnidade> findOne(Long id);

    /**
     * Delete the "id" tipoUnidade.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
