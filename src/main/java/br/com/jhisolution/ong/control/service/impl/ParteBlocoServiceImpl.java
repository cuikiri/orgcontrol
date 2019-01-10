package br.com.jhisolution.ong.control.service.impl;

import br.com.jhisolution.ong.control.service.ParteBlocoService;
import br.com.jhisolution.ong.control.domain.ParteBloco;
import br.com.jhisolution.ong.control.repository.ParteBlocoRepository;
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
 * Service Implementation for managing ParteBloco.
 */
@Service
@Transactional
public class ParteBlocoServiceImpl implements ParteBlocoService {

    private final Logger log = LoggerFactory.getLogger(ParteBlocoServiceImpl.class);

    private final ParteBlocoRepository parteBlocoRepository;

    public ParteBlocoServiceImpl(ParteBlocoRepository parteBlocoRepository) {
        this.parteBlocoRepository = parteBlocoRepository;
    }

    /**
     * Save a parteBloco.
     *
     * @param parteBloco the entity to save
     * @return the persisted entity
     */
    @Override
    public ParteBloco save(ParteBloco parteBloco) {
        log.debug("Request to save ParteBloco : {}", parteBloco);
        return parteBlocoRepository.save(parteBloco);
    }

    /**
     * Get all the parteBlocos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ParteBloco> findAll(Pageable pageable) {
        log.debug("Request to get all ParteBlocos");
        return parteBlocoRepository.findAll(pageable);
    }



    /**
     *  get all the parteBlocos where PeriodoAtividade is null.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<ParteBloco> findAllWherePeriodoAtividadeIsNull() {
        log.debug("Request to get all parteBlocos where PeriodoAtividade is null");
        return StreamSupport
            .stream(parteBlocoRepository.findAll().spliterator(), false)
            .filter(parteBloco -> parteBloco.getPeriodoAtividade() == null)
            .collect(Collectors.toList());
    }

    /**
     * Get one parteBloco by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ParteBloco> findOne(Long id) {
        log.debug("Request to get ParteBloco : {}", id);
        return parteBlocoRepository.findById(id);
    }

    /**
     * Delete the parteBloco by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ParteBloco : {}", id);
        parteBlocoRepository.deleteById(id);
    }
}
