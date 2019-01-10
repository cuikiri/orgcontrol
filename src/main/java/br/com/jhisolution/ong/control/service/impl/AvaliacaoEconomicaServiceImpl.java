package br.com.jhisolution.ong.control.service.impl;

import br.com.jhisolution.ong.control.service.AvaliacaoEconomicaService;
import br.com.jhisolution.ong.control.domain.AvaliacaoEconomica;
import br.com.jhisolution.ong.control.repository.AvaliacaoEconomicaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing AvaliacaoEconomica.
 */
@Service
@Transactional
public class AvaliacaoEconomicaServiceImpl implements AvaliacaoEconomicaService {

    private final Logger log = LoggerFactory.getLogger(AvaliacaoEconomicaServiceImpl.class);

    private final AvaliacaoEconomicaRepository avaliacaoEconomicaRepository;

    public AvaliacaoEconomicaServiceImpl(AvaliacaoEconomicaRepository avaliacaoEconomicaRepository) {
        this.avaliacaoEconomicaRepository = avaliacaoEconomicaRepository;
    }

    /**
     * Save a avaliacaoEconomica.
     *
     * @param avaliacaoEconomica the entity to save
     * @return the persisted entity
     */
    @Override
    public AvaliacaoEconomica save(AvaliacaoEconomica avaliacaoEconomica) {
        log.debug("Request to save AvaliacaoEconomica : {}", avaliacaoEconomica);
        return avaliacaoEconomicaRepository.save(avaliacaoEconomica);
    }

    /**
     * Get all the avaliacaoEconomicas.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<AvaliacaoEconomica> findAll(Pageable pageable) {
        log.debug("Request to get all AvaliacaoEconomicas");
        return avaliacaoEconomicaRepository.findAll(pageable);
    }


    /**
     * Get one avaliacaoEconomica by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<AvaliacaoEconomica> findOne(Long id) {
        log.debug("Request to get AvaliacaoEconomica : {}", id);
        return avaliacaoEconomicaRepository.findById(id);
    }

    /**
     * Delete the avaliacaoEconomica by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete AvaliacaoEconomica : {}", id);
        avaliacaoEconomicaRepository.deleteById(id);
    }
}
