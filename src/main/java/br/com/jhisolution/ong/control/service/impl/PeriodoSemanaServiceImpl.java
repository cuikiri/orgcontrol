package br.com.jhisolution.ong.control.service.impl;

import br.com.jhisolution.ong.control.service.PeriodoSemanaService;
import br.com.jhisolution.ong.control.domain.PeriodoSemana;
import br.com.jhisolution.ong.control.repository.PeriodoSemanaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing PeriodoSemana.
 */
@Service
@Transactional
public class PeriodoSemanaServiceImpl implements PeriodoSemanaService {

    private final Logger log = LoggerFactory.getLogger(PeriodoSemanaServiceImpl.class);

    private final PeriodoSemanaRepository periodoSemanaRepository;

    public PeriodoSemanaServiceImpl(PeriodoSemanaRepository periodoSemanaRepository) {
        this.periodoSemanaRepository = periodoSemanaRepository;
    }

    /**
     * Save a periodoSemana.
     *
     * @param periodoSemana the entity to save
     * @return the persisted entity
     */
    @Override
    public PeriodoSemana save(PeriodoSemana periodoSemana) {
        log.debug("Request to save PeriodoSemana : {}", periodoSemana);
        return periodoSemanaRepository.save(periodoSemana);
    }

    /**
     * Get all the periodoSemanas.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PeriodoSemana> findAll(Pageable pageable) {
        log.debug("Request to get all PeriodoSemanas");
        return periodoSemanaRepository.findAll(pageable);
    }


    /**
     * Get one periodoSemana by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PeriodoSemana> findOne(Long id) {
        log.debug("Request to get PeriodoSemana : {}", id);
        return periodoSemanaRepository.findById(id);
    }

    /**
     * Delete the periodoSemana by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete PeriodoSemana : {}", id);
        periodoSemanaRepository.deleteById(id);
    }
}
