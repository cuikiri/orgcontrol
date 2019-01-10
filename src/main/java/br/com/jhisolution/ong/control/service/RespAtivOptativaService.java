package br.com.jhisolution.ong.control.service;

import br.com.jhisolution.ong.control.domain.RespAtivOptativa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing RespAtivOptativa.
 */
public interface RespAtivOptativaService {

    /**
     * Save a respAtivOptativa.
     *
     * @param respAtivOptativa the entity to save
     * @return the persisted entity
     */
    RespAtivOptativa save(RespAtivOptativa respAtivOptativa);

    /**
     * Get all the respAtivOptativas.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<RespAtivOptativa> findAll(Pageable pageable);
    /**
     * Get all the RespAtivOptativaDTO where ItemAcompanhamentoAtividade is null.
     *
     * @return the list of entities
     */
    List<RespAtivOptativa> findAllWhereItemAcompanhamentoAtividadeIsNull();
    /**
     * Get all the RespAtivOptativaDTO where RespostaAtividade is null.
     *
     * @return the list of entities
     */
    List<RespAtivOptativa> findAllWhereRespostaAtividadeIsNull();


    /**
     * Get the "id" respAtivOptativa.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<RespAtivOptativa> findOne(Long id);

    /**
     * Delete the "id" respAtivOptativa.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
