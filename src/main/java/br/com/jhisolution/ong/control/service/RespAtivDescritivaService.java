package br.com.jhisolution.ong.control.service;

import br.com.jhisolution.ong.control.domain.RespAtivDescritiva;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing RespAtivDescritiva.
 */
public interface RespAtivDescritivaService {

    /**
     * Save a respAtivDescritiva.
     *
     * @param respAtivDescritiva the entity to save
     * @return the persisted entity
     */
    RespAtivDescritiva save(RespAtivDescritiva respAtivDescritiva);

    /**
     * Get all the respAtivDescritivas.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<RespAtivDescritiva> findAll(Pageable pageable);
    /**
     * Get all the RespAtivDescritivaDTO where ItemAcompanhamentoAtividade is null.
     *
     * @return the list of entities
     */
    List<RespAtivDescritiva> findAllWhereItemAcompanhamentoAtividadeIsNull();
    /**
     * Get all the RespAtivDescritivaDTO where RespostaAtividade is null.
     *
     * @return the list of entities
     */
    List<RespAtivDescritiva> findAllWhereRespostaAtividadeIsNull();


    /**
     * Get the "id" respAtivDescritiva.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<RespAtivDescritiva> findOne(Long id);

    /**
     * Delete the "id" respAtivDescritiva.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
