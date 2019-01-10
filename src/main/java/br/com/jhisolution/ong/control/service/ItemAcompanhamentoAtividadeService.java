package br.com.jhisolution.ong.control.service;

import br.com.jhisolution.ong.control.domain.ItemAcompanhamentoAtividade;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing ItemAcompanhamentoAtividade.
 */
public interface ItemAcompanhamentoAtividadeService {

    /**
     * Save a itemAcompanhamentoAtividade.
     *
     * @param itemAcompanhamentoAtividade the entity to save
     * @return the persisted entity
     */
    ItemAcompanhamentoAtividade save(ItemAcompanhamentoAtividade itemAcompanhamentoAtividade);

    /**
     * Get all the itemAcompanhamentoAtividades.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<ItemAcompanhamentoAtividade> findAll(Pageable pageable);


    /**
     * Get the "id" itemAcompanhamentoAtividade.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ItemAcompanhamentoAtividade> findOne(Long id);

    /**
     * Delete the "id" itemAcompanhamentoAtividade.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
