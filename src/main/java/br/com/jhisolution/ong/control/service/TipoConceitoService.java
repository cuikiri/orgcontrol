package br.com.jhisolution.ong.control.service;

import br.com.jhisolution.ong.control.domain.TipoConceito;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing TipoConceito.
 */
public interface TipoConceitoService {

    /**
     * Save a tipoConceito.
     *
     * @param tipoConceito the entity to save
     * @return the persisted entity
     */
    TipoConceito save(TipoConceito tipoConceito);

    /**
     * Get all the tipoConceitos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<TipoConceito> findAll(Pageable pageable);
    /**
     * Get all the TipoConceitoDTO where Conceito is null.
     *
     * @return the list of entities
     */
    List<TipoConceito> findAllWhereConceitoIsNull();


    /**
     * Get the "id" tipoConceito.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<TipoConceito> findOne(Long id);

    /**
     * Delete the "id" tipoConceito.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
