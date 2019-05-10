package br.com.jhisolution.ong.control.service.impl;

import br.com.jhisolution.ong.control.service.FotoDocumentoService;
import br.com.jhisolution.ong.control.domain.FotoDocumento;
import br.com.jhisolution.ong.control.repository.FotoDocumentoRepository;
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
 * Service Implementation for managing FotoDocumento.
 */
@Service
@Transactional
public class FotoDocumentoServiceImpl implements FotoDocumentoService {

    private final Logger log = LoggerFactory.getLogger(FotoDocumentoServiceImpl.class);

    private final FotoDocumentoRepository fotoDocumentoRepository;

    public FotoDocumentoServiceImpl(FotoDocumentoRepository fotoDocumentoRepository) {
        this.fotoDocumentoRepository = fotoDocumentoRepository;
    }

    /**
     * Save a fotoDocumento.
     *
     * @param fotoDocumento the entity to save
     * @return the persisted entity
     */
    @Override
    public FotoDocumento save(FotoDocumento fotoDocumento) {
        log.debug("Request to save FotoDocumento : {}", fotoDocumento);
        return fotoDocumentoRepository.save(fotoDocumento);
    }

    /**
     * Get all the fotoDocumentos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<FotoDocumento> findAll(Pageable pageable) {
        log.debug("Request to get all FotoDocumentos");
        return fotoDocumentoRepository.findAll(pageable);
    }

    /**
     * Get all the fotoDocumentos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<FotoDocumento> findAllByDocumento(Pageable pageable, Long id) {
        log.debug("Request to get all FotoDocumentos");
        return fotoDocumentoRepository.findAllByDocumento(pageable, id);
    }

    /**
     *  get all the fotoDocumentos where Documento is null.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<FotoDocumento> findAllWhereDocumentoIsNull() {
        log.debug("Request to get all fotoDocumentos where Documento is null");
        return StreamSupport
            .stream(fotoDocumentoRepository.findAll().spliterator(), false)
            .filter(fotoDocumento -> fotoDocumento.getDocumento() == null)
            .collect(Collectors.toList());
    }

    /**
     * Get one fotoDocumento by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<FotoDocumento> findOne(Long id) {
        log.debug("Request to get FotoDocumento : {}", id);
        return fotoDocumentoRepository.findById(id);
    }

    /**
     * Delete the fotoDocumento by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete FotoDocumento : {}", id);
        fotoDocumentoRepository.deleteById(id);
    }
}
