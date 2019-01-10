package br.com.jhisolution.ong.control.service.impl;

import br.com.jhisolution.ong.control.service.TipoAvaliacaoEconomicaService;
import br.com.jhisolution.ong.control.domain.TipoAvaliacaoEconomica;
import br.com.jhisolution.ong.control.repository.TipoAvaliacaoEconomicaRepository;
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
 * Service Implementation for managing TipoAvaliacaoEconomica.
 */
@Service
@Transactional
public class TipoAvaliacaoEconomicaServiceImpl implements TipoAvaliacaoEconomicaService {

    private final Logger log = LoggerFactory.getLogger(TipoAvaliacaoEconomicaServiceImpl.class);

    private final TipoAvaliacaoEconomicaRepository tipoAvaliacaoEconomicaRepository;

    public TipoAvaliacaoEconomicaServiceImpl(TipoAvaliacaoEconomicaRepository tipoAvaliacaoEconomicaRepository) {
        this.tipoAvaliacaoEconomicaRepository = tipoAvaliacaoEconomicaRepository;
    }

    /**
     * Save a tipoAvaliacaoEconomica.
     *
     * @param tipoAvaliacaoEconomica the entity to save
     * @return the persisted entity
     */
    @Override
    public TipoAvaliacaoEconomica save(TipoAvaliacaoEconomica tipoAvaliacaoEconomica) {
        log.debug("Request to save TipoAvaliacaoEconomica : {}", tipoAvaliacaoEconomica);
        return tipoAvaliacaoEconomicaRepository.save(tipoAvaliacaoEconomica);
    }

    /**
     * Get all the tipoAvaliacaoEconomicas.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TipoAvaliacaoEconomica> findAll(Pageable pageable) {
        log.debug("Request to get all TipoAvaliacaoEconomicas");
        return tipoAvaliacaoEconomicaRepository.findAll(pageable);
    }



    /**
     *  get all the tipoAvaliacaoEconomicas where AvaliacaoEconomica is null.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<TipoAvaliacaoEconomica> findAllWhereAvaliacaoEconomicaIsNull() {
        log.debug("Request to get all tipoAvaliacaoEconomicas where AvaliacaoEconomica is null");
        return StreamSupport
            .stream(tipoAvaliacaoEconomicaRepository.findAll().spliterator(), false)
            .filter(tipoAvaliacaoEconomica -> tipoAvaliacaoEconomica.getAvaliacaoEconomica() == null)
            .collect(Collectors.toList());
    }

    /**
     * Get one tipoAvaliacaoEconomica by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TipoAvaliacaoEconomica> findOne(Long id) {
        log.debug("Request to get TipoAvaliacaoEconomica : {}", id);
        return tipoAvaliacaoEconomicaRepository.findById(id);
    }

    /**
     * Delete the tipoAvaliacaoEconomica by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TipoAvaliacaoEconomica : {}", id);
        tipoAvaliacaoEconomicaRepository.deleteById(id);
    }
}
