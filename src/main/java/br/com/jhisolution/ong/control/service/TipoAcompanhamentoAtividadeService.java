package br.com.jhisolution.ong.control.service;

import br.com.jhisolution.ong.control.domain.TipoAcompanhamentoAtividade;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing TipoAcompanhamentoAtividade.
 */
public interface TipoAcompanhamentoAtividadeService {

    /**
     * Save a tipoAcompanhamentoAtividade.
     *
     * @param tipoAcompanhamentoAtividade the entity to save
     * @return the persisted entity
     */
    TipoAcompanhamentoAtividade save(TipoAcompanhamentoAtividade tipoAcompanhamentoAtividade);

    /**
     * Get all the tipoAcompanhamentoAtividades.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<TipoAcompanhamentoAtividade> findAll(Pageable pageable);
    /**
     * Get all the TipoAcompanhamentoAtividadeDTO where AcompanhamentoAtividade is null.
     *
     * @return the list of entities
     */
    List<TipoAcompanhamentoAtividade> findAllWhereAcompanhamentoAtividadeIsNull();


    /**
     * Get the "id" tipoAcompanhamentoAtividade.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<TipoAcompanhamentoAtividade> findOne(Long id);

    /**
     * Delete the "id" tipoAcompanhamentoAtividade.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
