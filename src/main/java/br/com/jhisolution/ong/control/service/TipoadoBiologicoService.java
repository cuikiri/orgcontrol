package br.com.jhisolution.ong.control.service;

import br.com.jhisolution.ong.control.domain.TipoadoBiologico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing TipoadoBiologico.
 */
public interface TipoadoBiologicoService {

    /**
     * Save a tipoadoBiologico.
     *
     * @param tipoadoBiologico the entity to save
     * @return the persisted entity
     */
    TipoadoBiologico save(TipoadoBiologico tipoadoBiologico);

    /**
     * Get all the tipoadoBiologicos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<TipoadoBiologico> findAll(Pageable pageable);
    /**
     * Get all the TipoadoBiologicoDTO where DadoBiologico is null.
     *
     * @return the list of entities
     */
    List<TipoadoBiologico> findAllWhereDadoBiologicoIsNull();


    /**
     * Get the "id" tipoadoBiologico.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<TipoadoBiologico> findOne(Long id);

    /**
     * Delete the "id" tipoadoBiologico.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
