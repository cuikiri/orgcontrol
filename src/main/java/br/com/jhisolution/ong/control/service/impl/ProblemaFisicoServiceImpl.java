package br.com.jhisolution.ong.control.service.impl;

import br.com.jhisolution.ong.control.service.ProblemaFisicoService;
import br.com.jhisolution.ong.control.domain.ProblemaFisico;
import br.com.jhisolution.ong.control.repository.ProblemaFisicoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing ProblemaFisico.
 */
@Service
@Transactional
public class ProblemaFisicoServiceImpl implements ProblemaFisicoService {

    private final Logger log = LoggerFactory.getLogger(ProblemaFisicoServiceImpl.class);

    private final ProblemaFisicoRepository problemaFisicoRepository;

    public ProblemaFisicoServiceImpl(ProblemaFisicoRepository problemaFisicoRepository) {
        this.problemaFisicoRepository = problemaFisicoRepository;
    }

    /**
     * Save a problemaFisico.
     *
     * @param problemaFisico the entity to save
     * @return the persisted entity
     */
    @Override
    public ProblemaFisico save(ProblemaFisico problemaFisico) {
        log.debug("Request to save ProblemaFisico : {}", problemaFisico);
        return problemaFisicoRepository.save(problemaFisico);
    }

    /**
     * Get all the problemaFisicos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ProblemaFisico> findAll(Pageable pageable) {
        log.debug("Request to get all ProblemaFisicos");
        return problemaFisicoRepository.findAll(pageable);
    }


    /**
     * Get one problemaFisico by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ProblemaFisico> findOne(Long id) {
        log.debug("Request to get ProblemaFisico : {}", id);
        return problemaFisicoRepository.findById(id);
    }

    /**
     * Delete the problemaFisico by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ProblemaFisico : {}", id);
        problemaFisicoRepository.deleteById(id);
    }
}
