package br.com.jhisolution.ong.control.service;

import br.com.jhisolution.ong.control.domain.Doenca;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Doenca.
 */
public interface DoencaService {

    /**
     * Save a doenca.
     *
     * @param doenca the entity to save
     * @return the persisted entity
     */
    Doenca save(Doenca doenca);

    /**
     * Get all the doencas.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Doenca> findAll(Pageable pageable);


    /**
     * Get the "id" doenca.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Doenca> findOne(Long id);

    /**
     * Delete the "id" doenca.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
