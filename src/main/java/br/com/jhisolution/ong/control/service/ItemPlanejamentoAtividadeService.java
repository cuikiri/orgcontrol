package br.com.jhisolution.ong.control.service;

import br.com.jhisolution.ong.control.domain.ItemPlanejamentoAtividade;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing ItemPlanejamentoAtividade.
 */
public interface ItemPlanejamentoAtividadeService {

    /**
     * Save a itemPlanejamentoAtividade.
     *
     * @param itemPlanejamentoAtividade the entity to save
     * @return the persisted entity
     */
    ItemPlanejamentoAtividade save(ItemPlanejamentoAtividade itemPlanejamentoAtividade);

    /**
     * Get all the itemPlanejamentoAtividades.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<ItemPlanejamentoAtividade> findAll(Pageable pageable);


    /**
     * Get the "id" itemPlanejamentoAtividade.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ItemPlanejamentoAtividade> findOne(Long id);

    /**
     * Delete the "id" itemPlanejamentoAtividade.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
