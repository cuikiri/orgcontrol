package br.com.jhisolution.ong.control.service;

import br.com.jhisolution.ong.control.domain.DadoBiologico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing DadoBiologico.
 */
public interface DadoBiologicoService {

    /**
     * Save a dadoBiologico.
     *
     * @param dadoBiologico the entity to save
     * @return the persisted entity
     */
    DadoBiologico save(DadoBiologico dadoBiologico);

    /**
     * Get all the dadoBiologicos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<DadoBiologico> findAll(Pageable pageable);
    /**
     * Get all the DadoBiologicoDTO where DadosMedico is null.
     *
     * @return the list of entities
     */
    List<DadoBiologico> findAllWhereDadosMedicoIsNull();


    /**
     * Get the "id" dadoBiologico.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<DadoBiologico> findOne(Long id);

    /**
     * Delete the "id" dadoBiologico.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
