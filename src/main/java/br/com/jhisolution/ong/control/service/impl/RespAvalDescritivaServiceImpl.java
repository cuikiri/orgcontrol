package br.com.jhisolution.ong.control.service.impl;

import br.com.jhisolution.ong.control.service.RespAvalDescritivaService;
import br.com.jhisolution.ong.control.domain.RespAvalDescritiva;
import br.com.jhisolution.ong.control.repository.RespAvalDescritivaRepository;
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
 * Service Implementation for managing RespAvalDescritiva.
 */
@Service
@Transactional
public class RespAvalDescritivaServiceImpl implements RespAvalDescritivaService {

    private final Logger log = LoggerFactory.getLogger(RespAvalDescritivaServiceImpl.class);

    private final RespAvalDescritivaRepository respAvalDescritivaRepository;

    public RespAvalDescritivaServiceImpl(RespAvalDescritivaRepository respAvalDescritivaRepository) {
        this.respAvalDescritivaRepository = respAvalDescritivaRepository;
    }

    /**
     * Save a respAvalDescritiva.
     *
     * @param respAvalDescritiva the entity to save
     * @return the persisted entity
     */
    @Override
    public RespAvalDescritiva save(RespAvalDescritiva respAvalDescritiva) {
        log.debug("Request to save RespAvalDescritiva : {}", respAvalDescritiva);
        return respAvalDescritivaRepository.save(respAvalDescritiva);
    }

    /**
     * Get all the respAvalDescritivas.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<RespAvalDescritiva> findAll(Pageable pageable) {
        log.debug("Request to get all RespAvalDescritivas");
        return respAvalDescritivaRepository.findAll(pageable);
    }



    /**
     *  get all the respAvalDescritivas where ItemAvaliacao is null.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<RespAvalDescritiva> findAllWhereItemAvaliacaoIsNull() {
        log.debug("Request to get all respAvalDescritivas where ItemAvaliacao is null");
        return StreamSupport
            .stream(respAvalDescritivaRepository.findAll().spliterator(), false)
            .filter(respAvalDescritiva -> respAvalDescritiva.getItemAvaliacao() == null)
            .collect(Collectors.toList());
    }


    /**
     *  get all the respAvalDescritivas where RespostaAvaliacao is null.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<RespAvalDescritiva> findAllWhereRespostaAvaliacaoIsNull() {
        log.debug("Request to get all respAvalDescritivas where RespostaAvaliacao is null");
        return StreamSupport
            .stream(respAvalDescritivaRepository.findAll().spliterator(), false)
            .filter(respAvalDescritiva -> respAvalDescritiva.getRespostaAvaliacao() == null)
            .collect(Collectors.toList());
    }

    /**
     * Get one respAvalDescritiva by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<RespAvalDescritiva> findOne(Long id) {
        log.debug("Request to get RespAvalDescritiva : {}", id);
        return respAvalDescritivaRepository.findById(id);
    }

    /**
     * Delete the respAvalDescritiva by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete RespAvalDescritiva : {}", id);
        respAvalDescritivaRepository.deleteById(id);
    }
}
