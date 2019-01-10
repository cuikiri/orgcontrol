package br.com.jhisolution.ong.control.service.impl;

import br.com.jhisolution.ong.control.service.OpcaoRespAvalOptativaService;
import br.com.jhisolution.ong.control.domain.OpcaoRespAvalOptativa;
import br.com.jhisolution.ong.control.repository.OpcaoRespAvalOptativaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing OpcaoRespAvalOptativa.
 */
@Service
@Transactional
public class OpcaoRespAvalOptativaServiceImpl implements OpcaoRespAvalOptativaService {

    private final Logger log = LoggerFactory.getLogger(OpcaoRespAvalOptativaServiceImpl.class);

    private final OpcaoRespAvalOptativaRepository opcaoRespAvalOptativaRepository;

    public OpcaoRespAvalOptativaServiceImpl(OpcaoRespAvalOptativaRepository opcaoRespAvalOptativaRepository) {
        this.opcaoRespAvalOptativaRepository = opcaoRespAvalOptativaRepository;
    }

    /**
     * Save a opcaoRespAvalOptativa.
     *
     * @param opcaoRespAvalOptativa the entity to save
     * @return the persisted entity
     */
    @Override
    public OpcaoRespAvalOptativa save(OpcaoRespAvalOptativa opcaoRespAvalOptativa) {
        log.debug("Request to save OpcaoRespAvalOptativa : {}", opcaoRespAvalOptativa);
        return opcaoRespAvalOptativaRepository.save(opcaoRespAvalOptativa);
    }

    /**
     * Get all the opcaoRespAvalOptativas.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<OpcaoRespAvalOptativa> findAll(Pageable pageable) {
        log.debug("Request to get all OpcaoRespAvalOptativas");
        return opcaoRespAvalOptativaRepository.findAll(pageable);
    }


    /**
     * Get one opcaoRespAvalOptativa by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<OpcaoRespAvalOptativa> findOne(Long id) {
        log.debug("Request to get OpcaoRespAvalOptativa : {}", id);
        return opcaoRespAvalOptativaRepository.findById(id);
    }

    /**
     * Delete the opcaoRespAvalOptativa by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete OpcaoRespAvalOptativa : {}", id);
        opcaoRespAvalOptativaRepository.deleteById(id);
    }
}
