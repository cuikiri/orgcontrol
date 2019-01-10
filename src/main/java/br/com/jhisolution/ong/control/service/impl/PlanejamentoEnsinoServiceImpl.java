package br.com.jhisolution.ong.control.service.impl;

import br.com.jhisolution.ong.control.service.PlanejamentoEnsinoService;
import br.com.jhisolution.ong.control.domain.PlanejamentoEnsino;
import br.com.jhisolution.ong.control.repository.PlanejamentoEnsinoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing PlanejamentoEnsino.
 */
@Service
@Transactional
public class PlanejamentoEnsinoServiceImpl implements PlanejamentoEnsinoService {

    private final Logger log = LoggerFactory.getLogger(PlanejamentoEnsinoServiceImpl.class);

    private final PlanejamentoEnsinoRepository planejamentoEnsinoRepository;

    public PlanejamentoEnsinoServiceImpl(PlanejamentoEnsinoRepository planejamentoEnsinoRepository) {
        this.planejamentoEnsinoRepository = planejamentoEnsinoRepository;
    }

    /**
     * Save a planejamentoEnsino.
     *
     * @param planejamentoEnsino the entity to save
     * @return the persisted entity
     */
    @Override
    public PlanejamentoEnsino save(PlanejamentoEnsino planejamentoEnsino) {
        log.debug("Request to save PlanejamentoEnsino : {}", planejamentoEnsino);
        return planejamentoEnsinoRepository.save(planejamentoEnsino);
    }

    /**
     * Get all the planejamentoEnsinos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PlanejamentoEnsino> findAll(Pageable pageable) {
        log.debug("Request to get all PlanejamentoEnsinos");
        return planejamentoEnsinoRepository.findAll(pageable);
    }


    /**
     * Get one planejamentoEnsino by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PlanejamentoEnsino> findOne(Long id) {
        log.debug("Request to get PlanejamentoEnsino : {}", id);
        return planejamentoEnsinoRepository.findById(id);
    }

    /**
     * Delete the planejamentoEnsino by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete PlanejamentoEnsino : {}", id);
        planejamentoEnsinoRepository.deleteById(id);
    }
}
