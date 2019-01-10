package br.com.jhisolution.ong.control.service;

import br.com.jhisolution.ong.control.domain.Avaliacao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Avaliacao.
 */
public interface AvaliacaoService {

    /**
     * Save a avaliacao.
     *
     * @param avaliacao the entity to save
     * @return the persisted entity
     */
    Avaliacao save(Avaliacao avaliacao);

    /**
     * Get all the avaliacaos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Avaliacao> findAll(Pageable pageable);


    /**
     * Get the "id" avaliacao.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Avaliacao> findOne(Long id);

    /**
     * Delete the "id" avaliacao.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
