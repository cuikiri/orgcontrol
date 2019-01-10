package br.com.jhisolution.ong.control.service.impl;

import br.com.jhisolution.ong.control.service.OpcaoRespAtividadeService;
import br.com.jhisolution.ong.control.domain.OpcaoRespAtividade;
import br.com.jhisolution.ong.control.repository.OpcaoRespAtividadeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing OpcaoRespAtividade.
 */
@Service
@Transactional
public class OpcaoRespAtividadeServiceImpl implements OpcaoRespAtividadeService {

    private final Logger log = LoggerFactory.getLogger(OpcaoRespAtividadeServiceImpl.class);

    private final OpcaoRespAtividadeRepository opcaoRespAtividadeRepository;

    public OpcaoRespAtividadeServiceImpl(OpcaoRespAtividadeRepository opcaoRespAtividadeRepository) {
        this.opcaoRespAtividadeRepository = opcaoRespAtividadeRepository;
    }

    /**
     * Save a opcaoRespAtividade.
     *
     * @param opcaoRespAtividade the entity to save
     * @return the persisted entity
     */
    @Override
    public OpcaoRespAtividade save(OpcaoRespAtividade opcaoRespAtividade) {
        log.debug("Request to save OpcaoRespAtividade : {}", opcaoRespAtividade);
        return opcaoRespAtividadeRepository.save(opcaoRespAtividade);
    }

    /**
     * Get all the opcaoRespAtividades.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<OpcaoRespAtividade> findAll(Pageable pageable) {
        log.debug("Request to get all OpcaoRespAtividades");
        return opcaoRespAtividadeRepository.findAll(pageable);
    }


    /**
     * Get one opcaoRespAtividade by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<OpcaoRespAtividade> findOne(Long id) {
        log.debug("Request to get OpcaoRespAtividade : {}", id);
        return opcaoRespAtividadeRepository.findById(id);
    }

    /**
     * Delete the opcaoRespAtividade by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete OpcaoRespAtividade : {}", id);
        opcaoRespAtividadeRepository.deleteById(id);
    }
}
