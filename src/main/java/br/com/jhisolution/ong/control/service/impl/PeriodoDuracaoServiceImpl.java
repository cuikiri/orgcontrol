package br.com.jhisolution.ong.control.service.impl;

import br.com.jhisolution.ong.control.service.PeriodoDuracaoService;
import br.com.jhisolution.ong.control.domain.PeriodoDuracao;
import br.com.jhisolution.ong.control.repository.PeriodoDuracaoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing PeriodoDuracao.
 */
@Service
@Transactional
public class PeriodoDuracaoServiceImpl implements PeriodoDuracaoService {

    private final Logger log = LoggerFactory.getLogger(PeriodoDuracaoServiceImpl.class);

    private final PeriodoDuracaoRepository periodoDuracaoRepository;

    public PeriodoDuracaoServiceImpl(PeriodoDuracaoRepository periodoDuracaoRepository) {
        this.periodoDuracaoRepository = periodoDuracaoRepository;
    }

    /**
     * Save a periodoDuracao.
     *
     * @param periodoDuracao the entity to save
     * @return the persisted entity
     */
    @Override
    public PeriodoDuracao save(PeriodoDuracao periodoDuracao) {
        log.debug("Request to save PeriodoDuracao : {}", periodoDuracao);
        return periodoDuracaoRepository.save(periodoDuracao);
    }

    /**
     * Get all the periodoDuracaos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PeriodoDuracao> findAll(Pageable pageable) {
        log.debug("Request to get all PeriodoDuracaos");
        return periodoDuracaoRepository.findAll(pageable);
    }


    /**
     * Get one periodoDuracao by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PeriodoDuracao> findOne(Long id) {
        log.debug("Request to get PeriodoDuracao : {}", id);
        return periodoDuracaoRepository.findById(id);
    }

    /**
     * Delete the periodoDuracao by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete PeriodoDuracao : {}", id);
        periodoDuracaoRepository.deleteById(id);
    }
}
