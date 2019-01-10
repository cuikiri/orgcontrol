package br.com.jhisolution.ong.control.service.impl;

import br.com.jhisolution.ong.control.service.RespostaAvaliacaoEconomicaService;
import br.com.jhisolution.ong.control.domain.RespostaAvaliacaoEconomica;
import br.com.jhisolution.ong.control.repository.RespostaAvaliacaoEconomicaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing RespostaAvaliacaoEconomica.
 */
@Service
@Transactional
public class RespostaAvaliacaoEconomicaServiceImpl implements RespostaAvaliacaoEconomicaService {

    private final Logger log = LoggerFactory.getLogger(RespostaAvaliacaoEconomicaServiceImpl.class);

    private final RespostaAvaliacaoEconomicaRepository respostaAvaliacaoEconomicaRepository;

    public RespostaAvaliacaoEconomicaServiceImpl(RespostaAvaliacaoEconomicaRepository respostaAvaliacaoEconomicaRepository) {
        this.respostaAvaliacaoEconomicaRepository = respostaAvaliacaoEconomicaRepository;
    }

    /**
     * Save a respostaAvaliacaoEconomica.
     *
     * @param respostaAvaliacaoEconomica the entity to save
     * @return the persisted entity
     */
    @Override
    public RespostaAvaliacaoEconomica save(RespostaAvaliacaoEconomica respostaAvaliacaoEconomica) {
        log.debug("Request to save RespostaAvaliacaoEconomica : {}", respostaAvaliacaoEconomica);
        return respostaAvaliacaoEconomicaRepository.save(respostaAvaliacaoEconomica);
    }

    /**
     * Get all the respostaAvaliacaoEconomicas.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<RespostaAvaliacaoEconomica> findAll(Pageable pageable) {
        log.debug("Request to get all RespostaAvaliacaoEconomicas");
        return respostaAvaliacaoEconomicaRepository.findAll(pageable);
    }


    /**
     * Get one respostaAvaliacaoEconomica by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<RespostaAvaliacaoEconomica> findOne(Long id) {
        log.debug("Request to get RespostaAvaliacaoEconomica : {}", id);
        return respostaAvaliacaoEconomicaRepository.findById(id);
    }

    /**
     * Delete the respostaAvaliacaoEconomica by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete RespostaAvaliacaoEconomica : {}", id);
        respostaAvaliacaoEconomicaRepository.deleteById(id);
    }
}
