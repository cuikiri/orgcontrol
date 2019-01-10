package br.com.jhisolution.ong.control.service.impl;

import br.com.jhisolution.ong.control.service.RespAvalOptativaEconomicaService;
import br.com.jhisolution.ong.control.domain.RespAvalOptativaEconomica;
import br.com.jhisolution.ong.control.repository.RespAvalOptativaEconomicaRepository;
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
 * Service Implementation for managing RespAvalOptativaEconomica.
 */
@Service
@Transactional
public class RespAvalOptativaEconomicaServiceImpl implements RespAvalOptativaEconomicaService {

    private final Logger log = LoggerFactory.getLogger(RespAvalOptativaEconomicaServiceImpl.class);

    private final RespAvalOptativaEconomicaRepository respAvalOptativaEconomicaRepository;

    public RespAvalOptativaEconomicaServiceImpl(RespAvalOptativaEconomicaRepository respAvalOptativaEconomicaRepository) {
        this.respAvalOptativaEconomicaRepository = respAvalOptativaEconomicaRepository;
    }

    /**
     * Save a respAvalOptativaEconomica.
     *
     * @param respAvalOptativaEconomica the entity to save
     * @return the persisted entity
     */
    @Override
    public RespAvalOptativaEconomica save(RespAvalOptativaEconomica respAvalOptativaEconomica) {
        log.debug("Request to save RespAvalOptativaEconomica : {}", respAvalOptativaEconomica);
        return respAvalOptativaEconomicaRepository.save(respAvalOptativaEconomica);
    }

    /**
     * Get all the respAvalOptativaEconomicas.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<RespAvalOptativaEconomica> findAll(Pageable pageable) {
        log.debug("Request to get all RespAvalOptativaEconomicas");
        return respAvalOptativaEconomicaRepository.findAll(pageable);
    }



    /**
     *  get all the respAvalOptativaEconomicas where ItemAvaliacaoEconomica is null.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<RespAvalOptativaEconomica> findAllWhereItemAvaliacaoEconomicaIsNull() {
        log.debug("Request to get all respAvalOptativaEconomicas where ItemAvaliacaoEconomica is null");
        return StreamSupport
            .stream(respAvalOptativaEconomicaRepository.findAll().spliterator(), false)
            .filter(respAvalOptativaEconomica -> respAvalOptativaEconomica.getItemAvaliacaoEconomica() == null)
            .collect(Collectors.toList());
    }


    /**
     *  get all the respAvalOptativaEconomicas where RespostaAvaliacaoEconomica is null.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<RespAvalOptativaEconomica> findAllWhereRespostaAvaliacaoEconomicaIsNull() {
        log.debug("Request to get all respAvalOptativaEconomicas where RespostaAvaliacaoEconomica is null");
        return StreamSupport
            .stream(respAvalOptativaEconomicaRepository.findAll().spliterator(), false)
            .filter(respAvalOptativaEconomica -> respAvalOptativaEconomica.getRespostaAvaliacaoEconomica() == null)
            .collect(Collectors.toList());
    }

    /**
     * Get one respAvalOptativaEconomica by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<RespAvalOptativaEconomica> findOne(Long id) {
        log.debug("Request to get RespAvalOptativaEconomica : {}", id);
        return respAvalOptativaEconomicaRepository.findById(id);
    }

    /**
     * Delete the respAvalOptativaEconomica by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete RespAvalOptativaEconomica : {}", id);
        respAvalOptativaEconomicaRepository.deleteById(id);
    }
}
