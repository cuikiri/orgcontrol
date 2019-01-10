package br.com.jhisolution.ong.control.service;

import br.com.jhisolution.ong.control.domain.TipoContratacao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing TipoContratacao.
 */
public interface TipoContratacaoService {

    /**
     * Save a tipoContratacao.
     *
     * @param tipoContratacao the entity to save
     * @return the persisted entity
     */
    TipoContratacao save(TipoContratacao tipoContratacao);

    /**
     * Get all the tipoContratacaos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<TipoContratacao> findAll(Pageable pageable);
    /**
     * Get all the TipoContratacaoDTO where Colaborador is null.
     *
     * @return the list of entities
     */
    List<TipoContratacao> findAllWhereColaboradorIsNull();


    /**
     * Get the "id" tipoContratacao.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<TipoContratacao> findOne(Long id);

    /**
     * Delete the "id" tipoContratacao.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
