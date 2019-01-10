package br.com.jhisolution.ong.control.service.impl;

import br.com.jhisolution.ong.control.service.RespAvalOptativaService;
import br.com.jhisolution.ong.control.domain.RespAvalOptativa;
import br.com.jhisolution.ong.control.repository.RespAvalOptativaRepository;
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
 * Service Implementation for managing RespAvalOptativa.
 */
@Service
@Transactional
public class RespAvalOptativaServiceImpl implements RespAvalOptativaService {

    private final Logger log = LoggerFactory.getLogger(RespAvalOptativaServiceImpl.class);

    private final RespAvalOptativaRepository respAvalOptativaRepository;

    public RespAvalOptativaServiceImpl(RespAvalOptativaRepository respAvalOptativaRepository) {
        this.respAvalOptativaRepository = respAvalOptativaRepository;
    }

    /**
     * Save a respAvalOptativa.
     *
     * @param respAvalOptativa the entity to save
     * @return the persisted entity
     */
    @Override
    public RespAvalOptativa save(RespAvalOptativa respAvalOptativa) {
        log.debug("Request to save RespAvalOptativa : {}", respAvalOptativa);
        return respAvalOptativaRepository.save(respAvalOptativa);
    }

    /**
     * Get all the respAvalOptativas.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<RespAvalOptativa> findAll(Pageable pageable) {
        log.debug("Request to get all RespAvalOptativas");
        return respAvalOptativaRepository.findAll(pageable);
    }



    /**
     *  get all the respAvalOptativas where ItemAvaliacao is null.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<RespAvalOptativa> findAllWhereItemAvaliacaoIsNull() {
        log.debug("Request to get all respAvalOptativas where ItemAvaliacao is null");
        return StreamSupport
            .stream(respAvalOptativaRepository.findAll().spliterator(), false)
            .filter(respAvalOptativa -> respAvalOptativa.getItemAvaliacao() == null)
            .collect(Collectors.toList());
    }


    /**
     *  get all the respAvalOptativas where RespostaAvaliacao is null.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<RespAvalOptativa> findAllWhereRespostaAvaliacaoIsNull() {
        log.debug("Request to get all respAvalOptativas where RespostaAvaliacao is null");
        return StreamSupport
            .stream(respAvalOptativaRepository.findAll().spliterator(), false)
            .filter(respAvalOptativa -> respAvalOptativa.getRespostaAvaliacao() == null)
            .collect(Collectors.toList());
    }

    /**
     * Get one respAvalOptativa by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<RespAvalOptativa> findOne(Long id) {
        log.debug("Request to get RespAvalOptativa : {}", id);
        return respAvalOptativaRepository.findById(id);
    }

    /**
     * Delete the respAvalOptativa by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete RespAvalOptativa : {}", id);
        respAvalOptativaRepository.deleteById(id);
    }
}
