package br.com.jhisolution.ong.control.service.impl;

import br.com.jhisolution.ong.control.service.AcompanhamentoAtividadeService;
import br.com.jhisolution.ong.control.domain.AcompanhamentoAtividade;
import br.com.jhisolution.ong.control.repository.AcompanhamentoAtividadeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing AcompanhamentoAtividade.
 */
@Service
@Transactional
public class AcompanhamentoAtividadeServiceImpl implements AcompanhamentoAtividadeService {

    private final Logger log = LoggerFactory.getLogger(AcompanhamentoAtividadeServiceImpl.class);

    private final AcompanhamentoAtividadeRepository acompanhamentoAtividadeRepository;

    public AcompanhamentoAtividadeServiceImpl(AcompanhamentoAtividadeRepository acompanhamentoAtividadeRepository) {
        this.acompanhamentoAtividadeRepository = acompanhamentoAtividadeRepository;
    }

    /**
     * Save a acompanhamentoAtividade.
     *
     * @param acompanhamentoAtividade the entity to save
     * @return the persisted entity
     */
    @Override
    public AcompanhamentoAtividade save(AcompanhamentoAtividade acompanhamentoAtividade) {
        log.debug("Request to save AcompanhamentoAtividade : {}", acompanhamentoAtividade);
        return acompanhamentoAtividadeRepository.save(acompanhamentoAtividade);
    }

    /**
     * Get all the acompanhamentoAtividades.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<AcompanhamentoAtividade> findAll(Pageable pageable) {
        log.debug("Request to get all AcompanhamentoAtividades");
        return acompanhamentoAtividadeRepository.findAll(pageable);
    }


    /**
     * Get one acompanhamentoAtividade by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<AcompanhamentoAtividade> findOne(Long id) {
        log.debug("Request to get AcompanhamentoAtividade : {}", id);
        return acompanhamentoAtividadeRepository.findById(id);
    }

    /**
     * Delete the acompanhamentoAtividade by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete AcompanhamentoAtividade : {}", id);
        acompanhamentoAtividadeRepository.deleteById(id);
    }
}
