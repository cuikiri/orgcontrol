package br.com.jhisolution.ong.control.service.impl;

import br.com.jhisolution.ong.control.service.EleicaoService;
import br.com.jhisolution.ong.control.domain.Eleicao;
import br.com.jhisolution.ong.control.repository.EleicaoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Eleicao.
 */
@Service
@Transactional
public class EleicaoServiceImpl implements EleicaoService {

    private final Logger log = LoggerFactory.getLogger(EleicaoServiceImpl.class);

    private final EleicaoRepository eleicaoRepository;

    public EleicaoServiceImpl(EleicaoRepository eleicaoRepository) {
        this.eleicaoRepository = eleicaoRepository;
    }

    /**
     * Save a eleicao.
     *
     * @param eleicao the entity to save
     * @return the persisted entity
     */
    @Override
    public Eleicao save(Eleicao eleicao) {
        log.debug("Request to save Eleicao : {}", eleicao);
        return eleicaoRepository.save(eleicao);
    }

    /**
     * Get all the eleicaos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Eleicao> findAll(Pageable pageable) {
        log.debug("Request to get all Eleicaos");
        return eleicaoRepository.findAll(pageable);
    }


    /**
     * Get one eleicao by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Eleicao> findOne(Long id) {
        log.debug("Request to get Eleicao : {}", id);
        return eleicaoRepository.findById(id);
    }

    /**
     * Delete the eleicao by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Eleicao : {}", id);
        eleicaoRepository.deleteById(id);
    }
}
