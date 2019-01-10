package br.com.jhisolution.ong.control.service.impl;

import br.com.jhisolution.ong.control.service.RespAvalDescritivaEconomicaService;
import br.com.jhisolution.ong.control.domain.RespAvalDescritivaEconomica;
import br.com.jhisolution.ong.control.repository.RespAvalDescritivaEconomicaRepository;
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
 * Service Implementation for managing RespAvalDescritivaEconomica.
 */
@Service
@Transactional
public class RespAvalDescritivaEconomicaServiceImpl implements RespAvalDescritivaEconomicaService {

    private final Logger log = LoggerFactory.getLogger(RespAvalDescritivaEconomicaServiceImpl.class);

    private final RespAvalDescritivaEconomicaRepository respAvalDescritivaEconomicaRepository;

    public RespAvalDescritivaEconomicaServiceImpl(RespAvalDescritivaEconomicaRepository respAvalDescritivaEconomicaRepository) {
        this.respAvalDescritivaEconomicaRepository = respAvalDescritivaEconomicaRepository;
    }

    /**
     * Save a respAvalDescritivaEconomica.
     *
     * @param respAvalDescritivaEconomica the entity to save
     * @return the persisted entity
     */
    @Override
    public RespAvalDescritivaEconomica save(RespAvalDescritivaEconomica respAvalDescritivaEconomica) {
        log.debug("Request to save RespAvalDescritivaEconomica : {}", respAvalDescritivaEconomica);
        return respAvalDescritivaEconomicaRepository.save(respAvalDescritivaEconomica);
    }

    /**
     * Get all the respAvalDescritivaEconomicas.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<RespAvalDescritivaEconomica> findAll(Pageable pageable) {
        log.debug("Request to get all RespAvalDescritivaEconomicas");
        return respAvalDescritivaEconomicaRepository.findAll(pageable);
    }



    /**
     *  get all the respAvalDescritivaEconomicas where ItemAvaliacaoEconomica is null.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<RespAvalDescritivaEconomica> findAllWhereItemAvaliacaoEconomicaIsNull() {
        log.debug("Request to get all respAvalDescritivaEconomicas where ItemAvaliacaoEconomica is null");
        return StreamSupport
            .stream(respAvalDescritivaEconomicaRepository.findAll().spliterator(), false)
            .filter(respAvalDescritivaEconomica -> respAvalDescritivaEconomica.getItemAvaliacaoEconomica() == null)
            .collect(Collectors.toList());
    }


    /**
     *  get all the respAvalDescritivaEconomicas where RespostaAvaliacaoEconomica is null.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<RespAvalDescritivaEconomica> findAllWhereRespostaAvaliacaoEconomicaIsNull() {
        log.debug("Request to get all respAvalDescritivaEconomicas where RespostaAvaliacaoEconomica is null");
        return StreamSupport
            .stream(respAvalDescritivaEconomicaRepository.findAll().spliterator(), false)
            .filter(respAvalDescritivaEconomica -> respAvalDescritivaEconomica.getRespostaAvaliacaoEconomica() == null)
            .collect(Collectors.toList());
    }

    /**
     * Get one respAvalDescritivaEconomica by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<RespAvalDescritivaEconomica> findOne(Long id) {
        log.debug("Request to get RespAvalDescritivaEconomica : {}", id);
        return respAvalDescritivaEconomicaRepository.findById(id);
    }

    /**
     * Delete the respAvalDescritivaEconomica by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete RespAvalDescritivaEconomica : {}", id);
        respAvalDescritivaEconomicaRepository.deleteById(id);
    }
}
