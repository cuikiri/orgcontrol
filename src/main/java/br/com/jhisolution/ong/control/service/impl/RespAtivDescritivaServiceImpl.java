package br.com.jhisolution.ong.control.service.impl;

import br.com.jhisolution.ong.control.service.RespAtivDescritivaService;
import br.com.jhisolution.ong.control.domain.RespAtivDescritiva;
import br.com.jhisolution.ong.control.repository.RespAtivDescritivaRepository;
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
 * Service Implementation for managing RespAtivDescritiva.
 */
@Service
@Transactional
public class RespAtivDescritivaServiceImpl implements RespAtivDescritivaService {

    private final Logger log = LoggerFactory.getLogger(RespAtivDescritivaServiceImpl.class);

    private final RespAtivDescritivaRepository respAtivDescritivaRepository;

    public RespAtivDescritivaServiceImpl(RespAtivDescritivaRepository respAtivDescritivaRepository) {
        this.respAtivDescritivaRepository = respAtivDescritivaRepository;
    }

    /**
     * Save a respAtivDescritiva.
     *
     * @param respAtivDescritiva the entity to save
     * @return the persisted entity
     */
    @Override
    public RespAtivDescritiva save(RespAtivDescritiva respAtivDescritiva) {
        log.debug("Request to save RespAtivDescritiva : {}", respAtivDescritiva);
        return respAtivDescritivaRepository.save(respAtivDescritiva);
    }

    /**
     * Get all the respAtivDescritivas.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<RespAtivDescritiva> findAll(Pageable pageable) {
        log.debug("Request to get all RespAtivDescritivas");
        return respAtivDescritivaRepository.findAll(pageable);
    }



    /**
     *  get all the respAtivDescritivas where ItemAcompanhamentoAtividade is null.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<RespAtivDescritiva> findAllWhereItemAcompanhamentoAtividadeIsNull() {
        log.debug("Request to get all respAtivDescritivas where ItemAcompanhamentoAtividade is null");
        return StreamSupport
            .stream(respAtivDescritivaRepository.findAll().spliterator(), false)
            .filter(respAtivDescritiva -> respAtivDescritiva.getItemAcompanhamentoAtividade() == null)
            .collect(Collectors.toList());
    }


    /**
     *  get all the respAtivDescritivas where RespostaAtividade is null.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<RespAtivDescritiva> findAllWhereRespostaAtividadeIsNull() {
        log.debug("Request to get all respAtivDescritivas where RespostaAtividade is null");
        return StreamSupport
            .stream(respAtivDescritivaRepository.findAll().spliterator(), false)
            .filter(respAtivDescritiva -> respAtivDescritiva.getRespostaAtividade() == null)
            .collect(Collectors.toList());
    }

    /**
     * Get one respAtivDescritiva by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<RespAtivDescritiva> findOne(Long id) {
        log.debug("Request to get RespAtivDescritiva : {}", id);
        return respAtivDescritivaRepository.findById(id);
    }

    /**
     * Delete the respAtivDescritiva by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete RespAtivDescritiva : {}", id);
        respAtivDescritivaRepository.deleteById(id);
    }
}
