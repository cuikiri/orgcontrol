package br.com.jhisolution.ong.control.service.impl;

import br.com.jhisolution.ong.control.service.TipoParteBlocoService;
import br.com.jhisolution.ong.control.domain.TipoParteBloco;
import br.com.jhisolution.ong.control.repository.TipoParteBlocoRepository;
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
 * Service Implementation for managing TipoParteBloco.
 */
@Service
@Transactional
public class TipoParteBlocoServiceImpl implements TipoParteBlocoService {

    private final Logger log = LoggerFactory.getLogger(TipoParteBlocoServiceImpl.class);

    private final TipoParteBlocoRepository tipoParteBlocoRepository;

    public TipoParteBlocoServiceImpl(TipoParteBlocoRepository tipoParteBlocoRepository) {
        this.tipoParteBlocoRepository = tipoParteBlocoRepository;
    }

    /**
     * Save a tipoParteBloco.
     *
     * @param tipoParteBloco the entity to save
     * @return the persisted entity
     */
    @Override
    public TipoParteBloco save(TipoParteBloco tipoParteBloco) {
        log.debug("Request to save TipoParteBloco : {}", tipoParteBloco);
        return tipoParteBlocoRepository.save(tipoParteBloco);
    }

    /**
     * Get all the tipoParteBlocos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TipoParteBloco> findAll(Pageable pageable) {
        log.debug("Request to get all TipoParteBlocos");
        return tipoParteBlocoRepository.findAll(pageable);
    }



    /**
     *  get all the tipoParteBlocos where ParteBloco is null.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<TipoParteBloco> findAllWhereParteBlocoIsNull() {
        log.debug("Request to get all tipoParteBlocos where ParteBloco is null");
        return StreamSupport
            .stream(tipoParteBlocoRepository.findAll().spliterator(), false)
            .filter(tipoParteBloco -> tipoParteBloco.getParteBloco() == null)
            .collect(Collectors.toList());
    }

    /**
     * Get one tipoParteBloco by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TipoParteBloco> findOne(Long id) {
        log.debug("Request to get TipoParteBloco : {}", id);
        return tipoParteBlocoRepository.findById(id);
    }

    /**
     * Delete the tipoParteBloco by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TipoParteBloco : {}", id);
        tipoParteBlocoRepository.deleteById(id);
    }
}
