package br.com.jhisolution.ong.control.service.impl;

import br.com.jhisolution.ong.control.service.InstituicaoService;
import br.com.jhisolution.ong.control.domain.Instituicao;
import br.com.jhisolution.ong.control.repository.InstituicaoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Instituicao.
 */
@Service
@Transactional
public class InstituicaoServiceImpl implements InstituicaoService {

    private final Logger log = LoggerFactory.getLogger(InstituicaoServiceImpl.class);

    private final InstituicaoRepository instituicaoRepository;

    public InstituicaoServiceImpl(InstituicaoRepository instituicaoRepository) {
        this.instituicaoRepository = instituicaoRepository;
    }

    /**
     * Save a instituicao.
     *
     * @param instituicao the entity to save
     * @return the persisted entity
     */
    @Override
    public Instituicao save(Instituicao instituicao) {
        log.debug("Request to save Instituicao : {}", instituicao);
        return instituicaoRepository.save(instituicao);
    }

    /**
     * Get all the instituicaos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Instituicao> findAll(Pageable pageable) {
        log.debug("Request to get all Instituicaos");
        return instituicaoRepository.findAll(pageable);
    }


    /**
     * Get one instituicao by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Instituicao> findOne(Long id) {
        log.debug("Request to get Instituicao : {}", id);
        return instituicaoRepository.findById(id);
    }

    /**
     * Delete the instituicao by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Instituicao : {}", id);
        instituicaoRepository.deleteById(id);
    }
}
