package br.com.jhisolution.ong.control.service.impl;

import br.com.jhisolution.ong.control.service.OpcaoRespAvalOptativaEconomicaService;
import br.com.jhisolution.ong.control.domain.OpcaoRespAvalOptativaEconomica;
import br.com.jhisolution.ong.control.repository.OpcaoRespAvalOptativaEconomicaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing OpcaoRespAvalOptativaEconomica.
 */
@Service
@Transactional
public class OpcaoRespAvalOptativaEconomicaServiceImpl implements OpcaoRespAvalOptativaEconomicaService {

    private final Logger log = LoggerFactory.getLogger(OpcaoRespAvalOptativaEconomicaServiceImpl.class);

    private final OpcaoRespAvalOptativaEconomicaRepository opcaoRespAvalOptativaEconomicaRepository;

    public OpcaoRespAvalOptativaEconomicaServiceImpl(OpcaoRespAvalOptativaEconomicaRepository opcaoRespAvalOptativaEconomicaRepository) {
        this.opcaoRespAvalOptativaEconomicaRepository = opcaoRespAvalOptativaEconomicaRepository;
    }

    /**
     * Save a opcaoRespAvalOptativaEconomica.
     *
     * @param opcaoRespAvalOptativaEconomica the entity to save
     * @return the persisted entity
     */
    @Override
    public OpcaoRespAvalOptativaEconomica save(OpcaoRespAvalOptativaEconomica opcaoRespAvalOptativaEconomica) {
        log.debug("Request to save OpcaoRespAvalOptativaEconomica : {}", opcaoRespAvalOptativaEconomica);
        return opcaoRespAvalOptativaEconomicaRepository.save(opcaoRespAvalOptativaEconomica);
    }

    /**
     * Get all the opcaoRespAvalOptativaEconomicas.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<OpcaoRespAvalOptativaEconomica> findAll(Pageable pageable) {
        log.debug("Request to get all OpcaoRespAvalOptativaEconomicas");
        return opcaoRespAvalOptativaEconomicaRepository.findAll(pageable);
    }


    /**
     * Get one opcaoRespAvalOptativaEconomica by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<OpcaoRespAvalOptativaEconomica> findOne(Long id) {
        log.debug("Request to get OpcaoRespAvalOptativaEconomica : {}", id);
        return opcaoRespAvalOptativaEconomicaRepository.findById(id);
    }

    /**
     * Delete the opcaoRespAvalOptativaEconomica by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete OpcaoRespAvalOptativaEconomica : {}", id);
        opcaoRespAvalOptativaEconomicaRepository.deleteById(id);
    }
}
