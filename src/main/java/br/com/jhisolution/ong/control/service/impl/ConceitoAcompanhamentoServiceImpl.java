package br.com.jhisolution.ong.control.service.impl;

import br.com.jhisolution.ong.control.service.ConceitoAcompanhamentoService;
import br.com.jhisolution.ong.control.domain.ConceitoAcompanhamento;
import br.com.jhisolution.ong.control.repository.ConceitoAcompanhamentoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing ConceitoAcompanhamento.
 */
@Service
@Transactional
public class ConceitoAcompanhamentoServiceImpl implements ConceitoAcompanhamentoService {

    private final Logger log = LoggerFactory.getLogger(ConceitoAcompanhamentoServiceImpl.class);

    private final ConceitoAcompanhamentoRepository conceitoAcompanhamentoRepository;

    public ConceitoAcompanhamentoServiceImpl(ConceitoAcompanhamentoRepository conceitoAcompanhamentoRepository) {
        this.conceitoAcompanhamentoRepository = conceitoAcompanhamentoRepository;
    }

    /**
     * Save a conceitoAcompanhamento.
     *
     * @param conceitoAcompanhamento the entity to save
     * @return the persisted entity
     */
    @Override
    public ConceitoAcompanhamento save(ConceitoAcompanhamento conceitoAcompanhamento) {
        log.debug("Request to save ConceitoAcompanhamento : {}", conceitoAcompanhamento);
        return conceitoAcompanhamentoRepository.save(conceitoAcompanhamento);
    }

    /**
     * Get all the conceitoAcompanhamentos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ConceitoAcompanhamento> findAll(Pageable pageable) {
        log.debug("Request to get all ConceitoAcompanhamentos");
        return conceitoAcompanhamentoRepository.findAll(pageable);
    }


    /**
     * Get one conceitoAcompanhamento by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ConceitoAcompanhamento> findOne(Long id) {
        log.debug("Request to get ConceitoAcompanhamento : {}", id);
        return conceitoAcompanhamentoRepository.findById(id);
    }

    /**
     * Delete the conceitoAcompanhamento by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ConceitoAcompanhamento : {}", id);
        conceitoAcompanhamentoRepository.deleteById(id);
    }
}
