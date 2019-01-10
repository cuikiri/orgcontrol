package br.com.jhisolution.ong.control.service;

import br.com.jhisolution.ong.control.domain.Eleicao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Eleicao.
 */
public interface EleicaoService {

    /**
     * Save a eleicao.
     *
     * @param eleicao the entity to save
     * @return the persisted entity
     */
    Eleicao save(Eleicao eleicao);

    /**
     * Get all the eleicaos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Eleicao> findAll(Pageable pageable);


    /**
     * Get the "id" eleicao.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Eleicao> findOne(Long id);

    /**
     * Delete the "id" eleicao.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
