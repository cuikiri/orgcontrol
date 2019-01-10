package br.com.jhisolution.ong.control.service.impl;

import br.com.jhisolution.ong.control.service.TipoBlocoService;
import br.com.jhisolution.ong.control.domain.TipoBloco;
import br.com.jhisolution.ong.control.repository.TipoBlocoRepository;
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
 * Service Implementation for managing TipoBloco.
 */
@Service
@Transactional
public class TipoBlocoServiceImpl implements TipoBlocoService {

    private final Logger log = LoggerFactory.getLogger(TipoBlocoServiceImpl.class);

    private final TipoBlocoRepository tipoBlocoRepository;

    public TipoBlocoServiceImpl(TipoBlocoRepository tipoBlocoRepository) {
        this.tipoBlocoRepository = tipoBlocoRepository;
    }

    /**
     * Save a tipoBloco.
     *
     * @param tipoBloco the entity to save
     * @return the persisted entity
     */
    @Override
    public TipoBloco save(TipoBloco tipoBloco) {
        log.debug("Request to save TipoBloco : {}", tipoBloco);
        return tipoBlocoRepository.save(tipoBloco);
    }

    /**
     * Get all the tipoBlocos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TipoBloco> findAll(Pageable pageable) {
        log.debug("Request to get all TipoBlocos");
        return tipoBlocoRepository.findAll(pageable);
    }



    /**
     *  get all the tipoBlocos where Bloco is null.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<TipoBloco> findAllWhereBlocoIsNull() {
        log.debug("Request to get all tipoBlocos where Bloco is null");
        return StreamSupport
            .stream(tipoBlocoRepository.findAll().spliterator(), false)
            .filter(tipoBloco -> tipoBloco.getBloco() == null)
            .collect(Collectors.toList());
    }

    /**
     * Get one tipoBloco by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TipoBloco> findOne(Long id) {
        log.debug("Request to get TipoBloco : {}", id);
        return tipoBlocoRepository.findById(id);
    }

    /**
     * Delete the tipoBloco by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TipoBloco : {}", id);
        tipoBlocoRepository.deleteById(id);
    }
}
