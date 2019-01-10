package br.com.jhisolution.ong.control.service;

import br.com.jhisolution.ong.control.domain.PlanejamentoAtividade;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing PlanejamentoAtividade.
 */
public interface PlanejamentoAtividadeService {

    /**
     * Save a planejamentoAtividade.
     *
     * @param planejamentoAtividade the entity to save
     * @return the persisted entity
     */
    PlanejamentoAtividade save(PlanejamentoAtividade planejamentoAtividade);

    /**
     * Get all the planejamentoAtividades.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<PlanejamentoAtividade> findAll(Pageable pageable);


    /**
     * Get the "id" planejamentoAtividade.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<PlanejamentoAtividade> findOne(Long id);

    /**
     * Delete the "id" planejamentoAtividade.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
