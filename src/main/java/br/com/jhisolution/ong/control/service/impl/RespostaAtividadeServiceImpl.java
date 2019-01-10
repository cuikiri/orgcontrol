package br.com.jhisolution.ong.control.service.impl;

import br.com.jhisolution.ong.control.service.RespostaAtividadeService;
import br.com.jhisolution.ong.control.domain.RespostaAtividade;
import br.com.jhisolution.ong.control.repository.RespostaAtividadeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing RespostaAtividade.
 */
@Service
@Transactional
public class RespostaAtividadeServiceImpl implements RespostaAtividadeService {

    private final Logger log = LoggerFactory.getLogger(RespostaAtividadeServiceImpl.class);

    private final RespostaAtividadeRepository respostaAtividadeRepository;

    public RespostaAtividadeServiceImpl(RespostaAtividadeRepository respostaAtividadeRepository) {
        this.respostaAtividadeRepository = respostaAtividadeRepository;
    }

    /**
     * Save a respostaAtividade.
     *
     * @param respostaAtividade the entity to save
     * @return the persisted entity
     */
    @Override
    public RespostaAtividade save(RespostaAtividade respostaAtividade) {
        log.debug("Request to save RespostaAtividade : {}", respostaAtividade);
        return respostaAtividadeRepository.save(respostaAtividade);
    }

    /**
     * Get all the respostaAtividades.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<RespostaAtividade> findAll(Pageable pageable) {
        log.debug("Request to get all RespostaAtividades");
        return respostaAtividadeRepository.findAll(pageable);
    }


    /**
     * Get one respostaAtividade by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<RespostaAtividade> findOne(Long id) {
        log.debug("Request to get RespostaAtividade : {}", id);
        return respostaAtividadeRepository.findById(id);
    }

    /**
     * Delete the respostaAtividade by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete RespostaAtividade : {}", id);
        respostaAtividadeRepository.deleteById(id);
    }
}
