package br.com.jhisolution.ong.control.service;

import br.com.jhisolution.ong.control.domain.OpcaoRespAtividade;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing OpcaoRespAtividade.
 */
public interface OpcaoRespAtividadeService {

    /**
     * Save a opcaoRespAtividade.
     *
     * @param opcaoRespAtividade the entity to save
     * @return the persisted entity
     */
    OpcaoRespAtividade save(OpcaoRespAtividade opcaoRespAtividade);

    /**
     * Get all the opcaoRespAtividades.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<OpcaoRespAtividade> findAll(Pageable pageable);


    /**
     * Get the "id" opcaoRespAtividade.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<OpcaoRespAtividade> findOne(Long id);

    /**
     * Delete the "id" opcaoRespAtividade.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
