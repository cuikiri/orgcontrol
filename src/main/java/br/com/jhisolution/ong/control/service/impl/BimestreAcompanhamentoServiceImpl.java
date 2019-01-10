package br.com.jhisolution.ong.control.service.impl;

import br.com.jhisolution.ong.control.service.BimestreAcompanhamentoService;
import br.com.jhisolution.ong.control.domain.BimestreAcompanhamento;
import br.com.jhisolution.ong.control.repository.BimestreAcompanhamentoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing BimestreAcompanhamento.
 */
@Service
@Transactional
public class BimestreAcompanhamentoServiceImpl implements BimestreAcompanhamentoService {

    private final Logger log = LoggerFactory.getLogger(BimestreAcompanhamentoServiceImpl.class);

    private final BimestreAcompanhamentoRepository bimestreAcompanhamentoRepository;

    public BimestreAcompanhamentoServiceImpl(BimestreAcompanhamentoRepository bimestreAcompanhamentoRepository) {
        this.bimestreAcompanhamentoRepository = bimestreAcompanhamentoRepository;
    }

    /**
     * Save a bimestreAcompanhamento.
     *
     * @param bimestreAcompanhamento the entity to save
     * @return the persisted entity
     */
    @Override
    public BimestreAcompanhamento save(BimestreAcompanhamento bimestreAcompanhamento) {
        log.debug("Request to save BimestreAcompanhamento : {}", bimestreAcompanhamento);
        return bimestreAcompanhamentoRepository.save(bimestreAcompanhamento);
    }

    /**
     * Get all the bimestreAcompanhamentos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<BimestreAcompanhamento> findAll(Pageable pageable) {
        log.debug("Request to get all BimestreAcompanhamentos");
        return bimestreAcompanhamentoRepository.findAll(pageable);
    }


    /**
     * Get one bimestreAcompanhamento by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<BimestreAcompanhamento> findOne(Long id) {
        log.debug("Request to get BimestreAcompanhamento : {}", id);
        return bimestreAcompanhamentoRepository.findById(id);
    }

    /**
     * Delete the bimestreAcompanhamento by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete BimestreAcompanhamento : {}", id);
        bimestreAcompanhamentoRepository.deleteById(id);
    }
}
