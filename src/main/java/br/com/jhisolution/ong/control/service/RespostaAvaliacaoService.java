package br.com.jhisolution.ong.control.service;

import br.com.jhisolution.ong.control.domain.RespostaAvaliacao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing RespostaAvaliacao.
 */
public interface RespostaAvaliacaoService {

    /**
     * Save a respostaAvaliacao.
     *
     * @param respostaAvaliacao the entity to save
     * @return the persisted entity
     */
    RespostaAvaliacao save(RespostaAvaliacao respostaAvaliacao);

    /**
     * Get all the respostaAvaliacaos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<RespostaAvaliacao> findAll(Pageable pageable);


    /**
     * Get the "id" respostaAvaliacao.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<RespostaAvaliacao> findOne(Long id);

    /**
     * Delete the "id" respostaAvaliacao.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
