package br.com.jhisolution.ong.control.service.impl;

import br.com.jhisolution.ong.control.service.DocumentoService;
import br.com.jhisolution.ong.control.domain.Documento;
import br.com.jhisolution.ong.control.domain.Pessoa;
import br.com.jhisolution.ong.control.repository.DocumentoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Documento.
 */
@Service
@Transactional
public class DocumentoServiceImpl implements DocumentoService {

    private final Logger log = LoggerFactory.getLogger(DocumentoServiceImpl.class);

    private final DocumentoRepository documentoRepository;

    public DocumentoServiceImpl(DocumentoRepository documentoRepository) {
        this.documentoRepository = documentoRepository;
    }

    /**
     * Save a documento.
     *
     * @param documento the entity to save
     * @return the persisted entity
     */
    @Override
    public Documento save(Documento documento) {
        log.debug("Request to save Documento : {}", documento);
        return documentoRepository.save(documento);
    }

    /**
     * Get all the documentos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Documento> findAll(Pageable pageable) {
        log.debug("Request to get all Documentos");
        return documentoRepository.findAll(pageable);
    }
    
    /**
     * Get all the documentos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Documento> findAllByPessoa(Pageable pageable, Pessoa pessoa) {
        log.debug("Request to get all Documentos");
        return documentoRepository.findAllByPessoa(pageable, pessoa);
    }

    /**
     * Get one documento by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Documento> findOne(Long id) {
        log.debug("Request to get Documento : {}", id);
        return documentoRepository.findById(id);
    }

    /**
     * Delete the documento by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Documento : {}", id);
        documentoRepository.deleteById(id);
    }
}
