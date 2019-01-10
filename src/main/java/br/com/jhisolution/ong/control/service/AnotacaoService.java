package br.com.jhisolution.ong.control.service;

import br.com.jhisolution.ong.control.domain.Anotacao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Anotacao.
 */
public interface AnotacaoService {

    /**
     * Save a anotacao.
     *
     * @param anotacao the entity to save
     * @return the persisted entity
     */
    Anotacao save(Anotacao anotacao);

    /**
     * Get all the anotacaos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Anotacao> findAll(Pageable pageable);


    /**
     * Get the "id" anotacao.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Anotacao> findOne(Long id);

    /**
     * Delete the "id" anotacao.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
