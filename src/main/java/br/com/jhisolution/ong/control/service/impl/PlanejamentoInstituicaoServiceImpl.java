package br.com.jhisolution.ong.control.service.impl;

import br.com.jhisolution.ong.control.service.PlanejamentoInstituicaoService;
import br.com.jhisolution.ong.control.domain.PlanejamentoInstituicao;
import br.com.jhisolution.ong.control.repository.PlanejamentoInstituicaoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing PlanejamentoInstituicao.
 */
@Service
@Transactional
public class PlanejamentoInstituicaoServiceImpl implements PlanejamentoInstituicaoService {

    private final Logger log = LoggerFactory.getLogger(PlanejamentoInstituicaoServiceImpl.class);

    private final PlanejamentoInstituicaoRepository planejamentoInstituicaoRepository;

    public PlanejamentoInstituicaoServiceImpl(PlanejamentoInstituicaoRepository planejamentoInstituicaoRepository) {
        this.planejamentoInstituicaoRepository = planejamentoInstituicaoRepository;
    }

    /**
     * Save a planejamentoInstituicao.
     *
     * @param planejamentoInstituicao the entity to save
     * @return the persisted entity
     */
    @Override
    public PlanejamentoInstituicao save(PlanejamentoInstituicao planejamentoInstituicao) {
        log.debug("Request to save PlanejamentoInstituicao : {}", planejamentoInstituicao);
        return planejamentoInstituicaoRepository.save(planejamentoInstituicao);
    }

    /**
     * Get all the planejamentoInstituicaos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PlanejamentoInstituicao> findAll(Pageable pageable) {
        log.debug("Request to get all PlanejamentoInstituicaos");
        return planejamentoInstituicaoRepository.findAll(pageable);
    }


    /**
     * Get one planejamentoInstituicao by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PlanejamentoInstituicao> findOne(Long id) {
        log.debug("Request to get PlanejamentoInstituicao : {}", id);
        return planejamentoInstituicaoRepository.findById(id);
    }

    /**
     * Delete the planejamentoInstituicao by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete PlanejamentoInstituicao : {}", id);
        planejamentoInstituicaoRepository.deleteById(id);
    }
}
