package br.com.jhisolution.ong.control.service;

import br.com.jhisolution.ong.control.domain.Conceito;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Conceito.
 */
public interface ConceitoService {

    /**
     * Save a conceito.
     *
     * @param conceito the entity to save
     * @return the persisted entity
     */
    Conceito save(Conceito conceito);

    /**
     * Get all the conceitos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Conceito> findAll(Pageable pageable);
    /**
     * Get all the ConceitoDTO where FechamentoBimestre is null.
     *
     * @return the list of entities
     */
    List<Conceito> findAllWhereFechamentoBimestreIsNull();


    /**
     * Get the "id" conceito.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Conceito> findOne(Long id);

    /**
     * Delete the "id" conceito.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
