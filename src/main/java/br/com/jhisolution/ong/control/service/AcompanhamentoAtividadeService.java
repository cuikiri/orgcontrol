package br.com.jhisolution.ong.control.service;

import br.com.jhisolution.ong.control.domain.AcompanhamentoAtividade;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing AcompanhamentoAtividade.
 */
public interface AcompanhamentoAtividadeService {

    /**
     * Save a acompanhamentoAtividade.
     *
     * @param acompanhamentoAtividade the entity to save
     * @return the persisted entity
     */
    AcompanhamentoAtividade save(AcompanhamentoAtividade acompanhamentoAtividade);

    /**
     * Get all the acompanhamentoAtividades.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<AcompanhamentoAtividade> findAll(Pageable pageable);


    /**
     * Get the "id" acompanhamentoAtividade.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<AcompanhamentoAtividade> findOne(Long id);

    /**
     * Delete the "id" acompanhamentoAtividade.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
