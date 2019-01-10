package br.com.jhisolution.ong.control.service.impl;

import br.com.jhisolution.ong.control.service.TipoDocumentoService;
import br.com.jhisolution.ong.control.domain.TipoDocumento;
import br.com.jhisolution.ong.control.repository.TipoDocumentoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service Implementation for managing TipoDocumento.
 */
@Service
@Transactional
public class TipoDocumentoServiceImpl implements TipoDocumentoService {

    private final Logger log = LoggerFactory.getLogger(TipoDocumentoServiceImpl.class);

    private final TipoDocumentoRepository tipoDocumentoRepository;

    public TipoDocumentoServiceImpl(TipoDocumentoRepository tipoDocumentoRepository) {
        this.tipoDocumentoRepository = tipoDocumentoRepository;
    }

    /**
     * Save a tipoDocumento.
     *
     * @param tipoDocumento the entity to save
     * @return the persisted entity
     */
    @Override
    public TipoDocumento save(TipoDocumento tipoDocumento) {
        log.debug("Request to save TipoDocumento : {}", tipoDocumento);
        return tipoDocumentoRepository.save(tipoDocumento);
    }

    /**
     * Get all the tipoDocumentos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TipoDocumento> findAll(Pageable pageable) {
        log.debug("Request to get all TipoDocumentos");
        return tipoDocumentoRepository.findAll(pageable);
    }



    /**
     *  get all the tipoDocumentos where Documento is null.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<TipoDocumento> findAllWhereDocumentoIsNull() {
        log.debug("Request to get all tipoDocumentos where Documento is null");
        return StreamSupport
            .stream(tipoDocumentoRepository.findAll().spliterator(), false)
            .filter(tipoDocumento -> tipoDocumento.getDocumento() == null)
            .collect(Collectors.toList());
    }

    /**
     * Get one tipoDocumento by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TipoDocumento> findOne(Long id) {
        log.debug("Request to get TipoDocumento : {}", id);
        return tipoDocumentoRepository.findById(id);
    }

    /**
     * Delete the tipoDocumento by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TipoDocumento : {}", id);
        tipoDocumentoRepository.deleteById(id);
    }
}
