package br.com.jhisolution.ong.control.service.impl;

import br.com.jhisolution.ong.control.service.AnotacaoService;
import br.com.jhisolution.ong.control.domain.Anotacao;
import br.com.jhisolution.ong.control.repository.AnotacaoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Anotacao.
 */
@Service
@Transactional
public class AnotacaoServiceImpl implements AnotacaoService {

    private final Logger log = LoggerFactory.getLogger(AnotacaoServiceImpl.class);

    private final AnotacaoRepository anotacaoRepository;

    public AnotacaoServiceImpl(AnotacaoRepository anotacaoRepository) {
        this.anotacaoRepository = anotacaoRepository;
    }

    /**
     * Save a anotacao.
     *
     * @param anotacao the entity to save
     * @return the persisted entity
     */
    @Override
    public Anotacao save(Anotacao anotacao) {
        log.debug("Request to save Anotacao : {}", anotacao);
        return anotacaoRepository.save(anotacao);
    }

    /**
     * Get all the anotacaos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Anotacao> findAll(Pageable pageable) {
        log.debug("Request to get all Anotacaos");
        return anotacaoRepository.findAll(pageable);
    }


    /**
     * Get one anotacao by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Anotacao> findOne(Long id) {
        log.debug("Request to get Anotacao : {}", id);
        return anotacaoRepository.findById(id);
    }

    /**
     * Delete the anotacao by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Anotacao : {}", id);
        anotacaoRepository.deleteById(id);
    }
}
