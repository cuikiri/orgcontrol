package br.com.jhisolution.ong.control.service.impl;

import br.com.jhisolution.ong.control.service.PlanejamentoAtividadeService;
import br.com.jhisolution.ong.control.domain.PlanejamentoAtividade;
import br.com.jhisolution.ong.control.repository.PlanejamentoAtividadeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing PlanejamentoAtividade.
 */
@Service
@Transactional
public class PlanejamentoAtividadeServiceImpl implements PlanejamentoAtividadeService {

    private final Logger log = LoggerFactory.getLogger(PlanejamentoAtividadeServiceImpl.class);

    private final PlanejamentoAtividadeRepository planejamentoAtividadeRepository;

    public PlanejamentoAtividadeServiceImpl(PlanejamentoAtividadeRepository planejamentoAtividadeRepository) {
        this.planejamentoAtividadeRepository = planejamentoAtividadeRepository;
    }

    /**
     * Save a planejamentoAtividade.
     *
     * @param planejamentoAtividade the entity to save
     * @return the persisted entity
     */
    @Override
    public PlanejamentoAtividade save(PlanejamentoAtividade planejamentoAtividade) {
        log.debug("Request to save PlanejamentoAtividade : {}", planejamentoAtividade);
        return planejamentoAtividadeRepository.save(planejamentoAtividade);
    }

    /**
     * Get all the planejamentoAtividades.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PlanejamentoAtividade> findAll(Pageable pageable) {
        log.debug("Request to get all PlanejamentoAtividades");
        return planejamentoAtividadeRepository.findAll(pageable);
    }


    /**
     * Get one planejamentoAtividade by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PlanejamentoAtividade> findOne(Long id) {
        log.debug("Request to get PlanejamentoAtividade : {}", id);
        return planejamentoAtividadeRepository.findById(id);
    }

    /**
     * Delete the planejamentoAtividade by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete PlanejamentoAtividade : {}", id);
        planejamentoAtividadeRepository.deleteById(id);
    }
}
