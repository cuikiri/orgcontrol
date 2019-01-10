package br.com.jhisolution.ong.control.service;

import br.com.jhisolution.ong.control.domain.TipoAvaliacao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing TipoAvaliacao.
 */
public interface TipoAvaliacaoService {

    /**
     * Save a tipoAvaliacao.
     *
     * @param tipoAvaliacao the entity to save
     * @return the persisted entity
     */
    TipoAvaliacao save(TipoAvaliacao tipoAvaliacao);

    /**
     * Get all the tipoAvaliacaos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<TipoAvaliacao> findAll(Pageable pageable);
    /**
     * Get all the TipoAvaliacaoDTO where Avaliacao is null.
     *
     * @return the list of entities
     */
    List<TipoAvaliacao> findAllWhereAvaliacaoIsNull();


    /**
     * Get the "id" tipoAvaliacao.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<TipoAvaliacao> findOne(Long id);

    /**
     * Delete the "id" tipoAvaliacao.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
