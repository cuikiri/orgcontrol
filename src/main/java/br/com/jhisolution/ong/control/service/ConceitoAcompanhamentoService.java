package br.com.jhisolution.ong.control.service;

import br.com.jhisolution.ong.control.domain.ConceitoAcompanhamento;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing ConceitoAcompanhamento.
 */
public interface ConceitoAcompanhamentoService {

    /**
     * Save a conceitoAcompanhamento.
     *
     * @param conceitoAcompanhamento the entity to save
     * @return the persisted entity
     */
    ConceitoAcompanhamento save(ConceitoAcompanhamento conceitoAcompanhamento);

    /**
     * Get all the conceitoAcompanhamentos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<ConceitoAcompanhamento> findAll(Pageable pageable);


    /**
     * Get the "id" conceitoAcompanhamento.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ConceitoAcompanhamento> findOne(Long id);

    /**
     * Delete the "id" conceitoAcompanhamento.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
