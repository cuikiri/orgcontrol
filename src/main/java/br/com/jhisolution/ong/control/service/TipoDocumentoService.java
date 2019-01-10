package br.com.jhisolution.ong.control.service;

import br.com.jhisolution.ong.control.domain.TipoDocumento;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing TipoDocumento.
 */
public interface TipoDocumentoService {

    /**
     * Save a tipoDocumento.
     *
     * @param tipoDocumento the entity to save
     * @return the persisted entity
     */
    TipoDocumento save(TipoDocumento tipoDocumento);

    /**
     * Get all the tipoDocumentos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<TipoDocumento> findAll(Pageable pageable);
    /**
     * Get all the TipoDocumentoDTO where Documento is null.
     *
     * @return the list of entities
     */
    List<TipoDocumento> findAllWhereDocumentoIsNull();


    /**
     * Get the "id" tipoDocumento.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<TipoDocumento> findOne(Long id);

    /**
     * Delete the "id" tipoDocumento.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
