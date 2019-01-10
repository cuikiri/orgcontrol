package br.com.jhisolution.ong.control.service;

import br.com.jhisolution.ong.control.domain.ItemPlanejamentoInstituicao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing ItemPlanejamentoInstituicao.
 */
public interface ItemPlanejamentoInstituicaoService {

    /**
     * Save a itemPlanejamentoInstituicao.
     *
     * @param itemPlanejamentoInstituicao the entity to save
     * @return the persisted entity
     */
    ItemPlanejamentoInstituicao save(ItemPlanejamentoInstituicao itemPlanejamentoInstituicao);

    /**
     * Get all the itemPlanejamentoInstituicaos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<ItemPlanejamentoInstituicao> findAll(Pageable pageable);


    /**
     * Get the "id" itemPlanejamentoInstituicao.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ItemPlanejamentoInstituicao> findOne(Long id);

    /**
     * Delete the "id" itemPlanejamentoInstituicao.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
