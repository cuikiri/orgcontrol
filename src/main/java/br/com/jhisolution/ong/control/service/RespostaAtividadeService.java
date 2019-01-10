package br.com.jhisolution.ong.control.service;

import br.com.jhisolution.ong.control.domain.RespostaAtividade;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing RespostaAtividade.
 */
public interface RespostaAtividadeService {

    /**
     * Save a respostaAtividade.
     *
     * @param respostaAtividade the entity to save
     * @return the persisted entity
     */
    RespostaAtividade save(RespostaAtividade respostaAtividade);

    /**
     * Get all the respostaAtividades.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<RespostaAtividade> findAll(Pageable pageable);


    /**
     * Get the "id" respostaAtividade.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<RespostaAtividade> findOne(Long id);

    /**
     * Delete the "id" respostaAtividade.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
