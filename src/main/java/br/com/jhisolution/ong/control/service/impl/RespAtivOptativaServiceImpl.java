package br.com.jhisolution.ong.control.service.impl;

import br.com.jhisolution.ong.control.service.RespAtivOptativaService;
import br.com.jhisolution.ong.control.domain.RespAtivOptativa;
import br.com.jhisolution.ong.control.repository.RespAtivOptativaRepository;
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
 * Service Implementation for managing RespAtivOptativa.
 */
@Service
@Transactional
public class RespAtivOptativaServiceImpl implements RespAtivOptativaService {

    private final Logger log = LoggerFactory.getLogger(RespAtivOptativaServiceImpl.class);

    private final RespAtivOptativaRepository respAtivOptativaRepository;

    public RespAtivOptativaServiceImpl(RespAtivOptativaRepository respAtivOptativaRepository) {
        this.respAtivOptativaRepository = respAtivOptativaRepository;
    }

    /**
     * Save a respAtivOptativa.
     *
     * @param respAtivOptativa the entity to save
     * @return the persisted entity
     */
    @Override
    public RespAtivOptativa save(RespAtivOptativa respAtivOptativa) {
        log.debug("Request to save RespAtivOptativa : {}", respAtivOptativa);
        return respAtivOptativaRepository.save(respAtivOptativa);
    }

    /**
     * Get all the respAtivOptativas.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<RespAtivOptativa> findAll(Pageable pageable) {
        log.debug("Request to get all RespAtivOptativas");
        return respAtivOptativaRepository.findAll(pageable);
    }



    /**
     *  get all the respAtivOptativas where ItemAcompanhamentoAtividade is null.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<RespAtivOptativa> findAllWhereItemAcompanhamentoAtividadeIsNull() {
        log.debug("Request to get all respAtivOptativas where ItemAcompanhamentoAtividade is null");
        return StreamSupport
            .stream(respAtivOptativaRepository.findAll().spliterator(), false)
            .filter(respAtivOptativa -> respAtivOptativa.getItemAcompanhamentoAtividade() == null)
            .collect(Collectors.toList());
    }


    /**
     *  get all the respAtivOptativas where RespostaAtividade is null.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<RespAtivOptativa> findAllWhereRespostaAtividadeIsNull() {
        log.debug("Request to get all respAtivOptativas where RespostaAtividade is null");
        return StreamSupport
            .stream(respAtivOptativaRepository.findAll().spliterator(), false)
            .filter(respAtivOptativa -> respAtivOptativa.getRespostaAtividade() == null)
            .collect(Collectors.toList());
    }

    /**
     * Get one respAtivOptativa by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<RespAtivOptativa> findOne(Long id) {
        log.debug("Request to get RespAtivOptativa : {}", id);
        return respAtivOptativaRepository.findById(id);
    }

    /**
     * Delete the respAtivOptativa by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete RespAtivOptativa : {}", id);
        respAtivOptativaRepository.deleteById(id);
    }
}
