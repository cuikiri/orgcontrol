package br.com.jhisolution.ong.control.service;

import br.com.jhisolution.ong.control.domain.BimestreAcompanhamento;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing BimestreAcompanhamento.
 */
public interface BimestreAcompanhamentoService {

    /**
     * Save a bimestreAcompanhamento.
     *
     * @param bimestreAcompanhamento the entity to save
     * @return the persisted entity
     */
    BimestreAcompanhamento save(BimestreAcompanhamento bimestreAcompanhamento);

    /**
     * Get all the bimestreAcompanhamentos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<BimestreAcompanhamento> findAll(Pageable pageable);


    /**
     * Get the "id" bimestreAcompanhamento.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<BimestreAcompanhamento> findOne(Long id);

    /**
     * Delete the "id" bimestreAcompanhamento.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
