package br.com.jhisolution.ong.control.service;

import br.com.jhisolution.ong.control.domain.ItemAvaliacao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing ItemAvaliacao.
 */
public interface ItemAvaliacaoService {

    /**
     * Save a itemAvaliacao.
     *
     * @param itemAvaliacao the entity to save
     * @return the persisted entity
     */
    ItemAvaliacao save(ItemAvaliacao itemAvaliacao);

    /**
     * Get all the itemAvaliacaos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<ItemAvaliacao> findAll(Pageable pageable);


    /**
     * Get the "id" itemAvaliacao.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ItemAvaliacao> findOne(Long id);

    /**
     * Delete the "id" itemAvaliacao.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
