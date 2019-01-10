package br.com.jhisolution.ong.control.service;

import br.com.jhisolution.ong.control.domain.RegistroRecuperacao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing RegistroRecuperacao.
 */
public interface RegistroRecuperacaoService {

    /**
     * Save a registroRecuperacao.
     *
     * @param registroRecuperacao the entity to save
     * @return the persisted entity
     */
    RegistroRecuperacao save(RegistroRecuperacao registroRecuperacao);

    /**
     * Get all the registroRecuperacaos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<RegistroRecuperacao> findAll(Pageable pageable);
    /**
     * Get all the RegistroRecuperacaoDTO where Diario is null.
     *
     * @return the list of entities
     */
    List<RegistroRecuperacao> findAllWhereDiarioIsNull();


    /**
     * Get the "id" registroRecuperacao.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<RegistroRecuperacao> findOne(Long id);

    /**
     * Delete the "id" registroRecuperacao.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
