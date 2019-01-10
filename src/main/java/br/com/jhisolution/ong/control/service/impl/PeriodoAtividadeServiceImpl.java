package br.com.jhisolution.ong.control.service.impl;

import br.com.jhisolution.ong.control.service.PeriodoAtividadeService;
import br.com.jhisolution.ong.control.domain.PeriodoAtividade;
import br.com.jhisolution.ong.control.repository.PeriodoAtividadeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing PeriodoAtividade.
 */
@Service
@Transactional
public class PeriodoAtividadeServiceImpl implements PeriodoAtividadeService {

    private final Logger log = LoggerFactory.getLogger(PeriodoAtividadeServiceImpl.class);

    private final PeriodoAtividadeRepository periodoAtividadeRepository;

    public PeriodoAtividadeServiceImpl(PeriodoAtividadeRepository periodoAtividadeRepository) {
        this.periodoAtividadeRepository = periodoAtividadeRepository;
    }

    /**
     * Save a periodoAtividade.
     *
     * @param periodoAtividade the entity to save
     * @return the persisted entity
     */
    @Override
    public PeriodoAtividade save(PeriodoAtividade periodoAtividade) {
        log.debug("Request to save PeriodoAtividade : {}", periodoAtividade);
        return periodoAtividadeRepository.save(periodoAtividade);
    }

    /**
     * Get all the periodoAtividades.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PeriodoAtividade> findAll(Pageable pageable) {
        log.debug("Request to get all PeriodoAtividades");
        return periodoAtividadeRepository.findAll(pageable);
    }


    /**
     * Get one periodoAtividade by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PeriodoAtividade> findOne(Long id) {
        log.debug("Request to get PeriodoAtividade : {}", id);
        return periodoAtividadeRepository.findById(id);
    }

    /**
     * Delete the periodoAtividade by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete PeriodoAtividade : {}", id);
        periodoAtividadeRepository.deleteById(id);
    }
}
